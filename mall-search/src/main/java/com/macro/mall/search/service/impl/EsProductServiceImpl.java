package com.macro.mall.search.service.impl;

import com.macro.mall.search.dao.EsProductDao;
import com.macro.mall.search.domain.EsProduct;
import com.macro.mall.search.domain.EsProductRelatedInfo;
import com.macro.mall.search.dto.AggProduct;
import com.macro.mall.search.dto.QueryProduct;
import com.macro.mall.search.repository.EsProductRepository;
import com.macro.mall.search.service.EsProductService;
import com.macro.mall.search.util.TreeNode;
import com.macro.mall.search.util.TreeUtil;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.InternalFilter;
import org.elasticsearch.search.aggregations.bucket.nested.InternalNested;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 * 商品搜索管理Service实现类
 * Created by macro on 2018/6/19.
 */
@Service
public class EsProductServiceImpl implements EsProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsProductServiceImpl.class);
    @Autowired
    private EsProductDao productDao;
    @Autowired
    private EsProductRepository productRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int importAll() {
        List<EsProduct> esProductList = productDao.getAllEsProductList(null);

        Map<Long, List<Long>> mProductCategoryIds = loadProductCategoryIds();
        setProductCategoryIds(esProductList, mProductCategoryIds);

        Iterable<EsProduct> esProductIterable = productRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = esProductIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    private Map<Long, List<Long>> loadProductCategoryIds(){
        Map<Long, List<Long>> mProductCategoryIds = new HashMap<>();
        // 从数据库获取分类列表，构造成tree后整理为map备用
        String sql = "select id, parent_id, name from pms_product_category order by parent_id, id";
        List<TreeNode> nodeList = jdbcTemplate.query(sql, new RowMapper<TreeNode>() {
            public TreeNode mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TreeNode(rs.getString("id"), rs.getString("parent_id"), rs.getString("name"));
            }
        });
        List<TreeNode> treeNodes = TreeUtil.list2tree(nodeList);
        loadProductCategoryIdsFromTree(mProductCategoryIds, treeNodes);
        return  mProductCategoryIds;
    }

    private static void loadProductCategoryIdsFromTree(Map<Long, List<Long>> mProductCategoryIds, List<TreeNode> treeNodes){
        if(treeNodes==null || treeNodes.isEmpty()){
            return;
        }
        for(TreeNode node: treeNodes){
            Long id = Long.valueOf(node.getId());
            Long parentId = Long.valueOf(node.getParentId());
            List<Long> ids = new ArrayList<Long>();
            ids.add(id);
            if(mProductCategoryIds.containsKey(parentId)){
                ids.addAll(mProductCategoryIds.get(parentId));
            }
            mProductCategoryIds.put(id, ids);
            if(node.getChildren()!=null){
                loadProductCategoryIdsFromTree(mProductCategoryIds, node.getChildren());
            }
        }
    }

    private void setProductCategoryIds(List<EsProduct> esProductList, Map<Long, List<Long>> mProductCategoryIds){
        if(mProductCategoryIds==null || mProductCategoryIds.isEmpty() || esProductList==null || esProductList.isEmpty()){
            return;
        }
        for(EsProduct product: esProductList){
            Long productCategoryId = product.getProductCategoryId();
            if(productCategoryId!=null && mProductCategoryIds.containsKey(productCategoryId)){
                product.setProductCategoryIds(mProductCategoryIds.get(productCategoryId));
            }
        }
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public EsProduct create(Long id) {
        EsProduct result = null;
        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
        if (esProductList.size() > 0) {
            EsProduct esProduct = esProductList.get(0);
            result = productRepository.save(esProduct);
        }
        return result;
    }

    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsProduct> esProductList = new ArrayList<>();
            for (Long id : ids) {
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProductList.add(esProduct);
            }
            productRepository.deleteAll(esProductList);
        }
    }

    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
    }

    @Override
    public Page<EsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize,Integer sort) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //分页
        nativeSearchQueryBuilder.withPageable(pageable);
        //过滤
        if (brandId != null || productCategoryId != null) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if (brandId != null) {
                boolQueryBuilder.must(QueryBuilders.termQuery("brandId", brandId));
            }
            if (productCategoryId != null) {
                boolQueryBuilder.must(QueryBuilders.termQuery("productCategoryId", productCategoryId));
            }
            nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
        }
        //搜索
        if (StringUtils.isEmpty(keyword)) {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        } else {
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("name", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(10)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("subTitle", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(5)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("keywords", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        }
        //排序
        if(sort==1){
            //按新品从新到旧
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
        }else if(sort==2){
            //按销量从高到低
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("sale").order(SortOrder.DESC));
        }else if(sort==3){
            //按价格从低到高
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        }else if(sort==4){
            //按价格从高到低
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        }else{
            //按相关度
            nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        }
        nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        LOGGER.info("DSL:{}", searchQuery.getQuery().toString());
        return productRepository.search(searchQuery);
    }

    @Override
    public Page<EsProduct> recommend(Long id, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
        if (esProductList.size() > 0) {
            EsProduct esProduct = esProductList.get(0);
            String keyword = esProduct.getName();
            Long brandId = esProduct.getBrandId();
            Long productCategoryId = esProduct.getProductCategoryId();
            //根据商品标题、品牌、分类进行搜索
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("name", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(8)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("subTitle", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("keywords", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("brandId", brandId),
                    ScoreFunctionBuilders.weightFactorFunction(5)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("productCategoryId", productCategoryId),
                    ScoreFunctionBuilders.weightFactorFunction(3)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            //用于过滤掉相同的商品
            BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
            boolQueryBuilder.mustNot(QueryBuilders.termQuery("id",id));
            //构建查询条件
            NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
            builder.withQuery(functionScoreQueryBuilder);
            builder.withFilter(boolQueryBuilder);
            builder.withPageable(pageable);
            NativeSearchQuery searchQuery = builder.build();
            LOGGER.info("DSL:{}", searchQuery.getQuery().toString());
            return productRepository.search(searchQuery);
        }
        return new PageImpl<>(null);
    }

    @Override
    public EsProductRelatedInfo searchRelatedInfo(String keyword) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //搜索条件
        if(StringUtils.isEmpty(keyword)){
            builder.withQuery(QueryBuilders.matchAllQuery());
        }else{
            builder.withQuery(QueryBuilders.multiMatchQuery(keyword,"name","subTitle","keywords"));
        }
        //聚合搜索品牌名称
        builder.addAggregation(AggregationBuilders.terms("brandNames").field("brandName"));
        //集合搜索分类名称
        builder.addAggregation(AggregationBuilders.terms("productCategoryNames").field("productCategoryName"));
        //聚合搜索商品属性，去除type=1的属性
        AbstractAggregationBuilder aggregationBuilder = AggregationBuilders.nested("allAttrValues","attrValueList")
                .subAggregation(AggregationBuilders.filter("productAttrs",QueryBuilders.termQuery("attrValueList.type",1))
                        .subAggregation(AggregationBuilders.terms("attrIds")
                                .field("attrValueList.productAttributeId")
                                .subAggregation(AggregationBuilders.terms("attrValues")
                                        .field("attrValueList.value"))
                                .subAggregation(AggregationBuilders.terms("attrNames")
                                        .field("attrValueList.name"))));
        builder.addAggregation(aggregationBuilder);
        NativeSearchQuery searchQuery = builder.build();
        return elasticsearchTemplate.query(searchQuery, response -> {
            LOGGER.info("DSL:{}",searchQuery.getQuery().toString());
            return convertProductRelatedInfo(response);
        });
    }

    /**
     * 将返回结果转换为对象
     */
    private EsProductRelatedInfo convertProductRelatedInfo(SearchResponse response) {
        EsProductRelatedInfo productRelatedInfo = new EsProductRelatedInfo();
        Map<String, Aggregation> aggregationMap = response.getAggregations().getAsMap();
        //设置品牌
        Aggregation brandNames = aggregationMap.get("brandNames");
        List<String> brandNameList = new ArrayList<>();
        for(int i = 0; i<((Terms) brandNames).getBuckets().size(); i++){
            brandNameList.add(((Terms) brandNames).getBuckets().get(i).getKeyAsString());
        }
        productRelatedInfo.setBrandNames(brandNameList);
        //设置分类
        Aggregation productCategoryNames = aggregationMap.get("productCategoryNames");
        List<String> productCategoryNameList = new ArrayList<>();
        for(int i=0;i<((Terms) productCategoryNames).getBuckets().size();i++){
            productCategoryNameList.add(((Terms) productCategoryNames).getBuckets().get(i).getKeyAsString());
        }
        productRelatedInfo.setProductCategoryNames(productCategoryNameList);
        //设置参数
        Aggregation productAttrs = aggregationMap.get("allAttrValues");
        List<LongTerms.Bucket> attrIds = ((LongTerms) ((InternalFilter) ((InternalNested) productAttrs).getProperty("productAttrs")).getProperty("attrIds")).getBuckets();
        List<EsProductRelatedInfo.ProductAttr> attrList = new ArrayList<>();
        for (Terms.Bucket attrId : attrIds) {
            EsProductRelatedInfo.ProductAttr attr = new EsProductRelatedInfo.ProductAttr();
            attr.setAttrId((Long) attrId.getKey());
            List<String> attrValueList = new ArrayList<>();
            List<StringTerms.Bucket> attrValues = ((StringTerms) attrId.getAggregations().get("attrValues")).getBuckets();
            List<StringTerms.Bucket> attrNames = ((StringTerms) attrId.getAggregations().get("attrNames")).getBuckets();
            for (Terms.Bucket attrValue : attrValues) {
                attrValueList.add(attrValue.getKeyAsString());
            }
            attr.setAttrValues(attrValueList);
            if(!CollectionUtils.isEmpty(attrNames)){
                String attrName = attrNames.get(0).getKeyAsString();
                attr.setAttrName(attrName);
            }
            attrList.add(attr);
        }
        productRelatedInfo.setProductAttrs(attrList);
        return productRelatedInfo;
    }

    /////////////////////////////////////////////////

    /**
     * 初始化Index (fix数据导致的mapping异常)
     * @return
     */
    public boolean initIndex(){
        elasticsearchTemplate.deleteIndex(EsProduct.class);
        elasticsearchTemplate.createIndex(EsProduct.class);
        elasticsearchTemplate.putMapping(EsProduct.class);
        elasticsearchTemplate.refresh(EsProduct.class);
        return true;
    }

    /**
     * 商品分页查询
     * @param query
     * @return
     */
    @Override
    public Page<EsProduct> search(QueryProduct query) {

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //分页
        Pageable pageable = PageRequest.of(query.getPage()-1, query.getSize());
        nativeSearchQueryBuilder.withPageable(pageable);
        //过滤
        BoolQueryBuilder boolQueryBuilder = this.addFilters(query);
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        //排序
        SortBuilder sortBuilder = addSort(query);
        nativeSearchQueryBuilder.withSort(sortBuilder);

        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        LOGGER.info("DSL:{}", searchQuery.getQuery().toString());
        return productRepository.search(searchQuery);
    }


    /**
     * 筛选条件聚合查询接口
     * @param query
     * @return
     */
    @Override
    public AggProduct aggProduct(QueryProduct query) {
        //检索条件
        BoolQueryBuilder boolQueryBuilder = this.addFilters(query);

        // 品牌聚合（取命中最多的前500个品牌）
        TermsAggregationBuilder brandAggBuilder = AggregationBuilders.terms("brandNames").field("brandName").size(100);
        // 分类聚合（取命中最多的前20个）
        TermsAggregationBuilder categoryAggBuilder = AggregationBuilders.terms("productCategoryNames").field("productCategoryName").size(20);

        //聚合搜索商品属性
        AbstractAggregationBuilder aggregationBuilder = AggregationBuilders.nested("allAttrValues","attrValueList")
                //QueryBuilders.termQuery("attrValueList.type",1)  //去除type=1的属性
                .subAggregation(AggregationBuilders.filter("productAttrs",QueryBuilders.matchAllQuery())
                        .subAggregation(AggregationBuilders.terms("attrIds")
                                .field("attrValueList.productAttributeId")
                                .subAggregation(AggregationBuilders.terms("attrValues")
                                        .field("attrValueList.value"))
                                .subAggregation(AggregationBuilders.terms("attrNames")
                                        .field("attrValueList.name"))));

        //构建查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder) // 过滤条件
                .addAggregation(brandAggBuilder) // 品牌聚合
                .addAggregation(categoryAggBuilder) // 分类聚合
                .addAggregation(aggregationBuilder) // 规格参数聚合
                .build();

        return elasticsearchTemplate.query(searchQuery, response -> {
            LOGGER.info("DSL:{}",searchQuery.getQuery().toString());
            return convertAggProduct(response);
        });
    }

    /**
     * 过滤条件
     * @param query
     * @return
     */
    private BoolQueryBuilder addFilters(QueryProduct query) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (query.getId() != null) {
            // 商品Id查询
            boolQueryBuilder.filter(QueryBuilders.matchQuery("id", query.getId()).operator(Operator.AND));
        }
        if (query.getProductSn() != null) {
            // 商品SN查询
            boolQueryBuilder.filter(QueryBuilders.matchQuery("productSn", query.getProductSn()).operator(Operator.AND));
        }
        if (query.getBrandId() != null) {
            // 品牌ID查询
            boolQueryBuilder.must(QueryBuilders.termQuery("brandId", query.getBrandId()));
        }
        if (!StringUtils.isEmpty(query.getBrandName())) {
            // 品牌查询
            boolQueryBuilder.filter(QueryBuilders.termsQuery("brandName", query.getBrandName()));
        }
        if (query.getProductCategoryId() != null) {
            // 分类查询
            //boolQueryBuilder.filter(QueryBuilders.matchQuery("productCategoryId", query.getProductCategoryId()).operator(Operator.AND));
            boolQueryBuilder.filter(QueryBuilders.multiMatchQuery(query.getProductCategoryId(), "productCategoryId", "productCategoryIds").operator(Operator.AND));
        }
        if (!StringUtils.isEmpty(query.getProductCategoryName())) {
            // 分类名称查询
            boolQueryBuilder.filter(QueryBuilders.termsQuery("productCategoryName", query.getProductCategoryName()));
        }

        // 价格范围
        if(query.getMinPrice()!=null && query.getMinPrice().doubleValue()>0.1){
            // 最低价格
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(query.getMinPrice().doubleValue()));
        }
        if(query.getMaxPrice()!=null && query.getMaxPrice().doubleValue()>0.1
                && (query.getMinPrice()==null || query.getMaxPrice().doubleValue()>query.getMinPrice().doubleValue()) ){
            // 最高价格
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").lte(query.getMaxPrice().doubleValue()));
        }

        List<QueryProduct.ProductAttr> attrGroups = query.getProductAttrs();
        if (attrGroups != null) {
            // 多属性过滤查询，如（内存、颜色、屏幕尺寸、版本等等）
            for (QueryProduct.ProductAttr attrGroup : attrGroups) {
                BoolQueryBuilder attrBoolQuery = QueryBuilders.boolQuery();
                // 匹配商品规格参数名
                attrBoolQuery.filter(QueryBuilders.matchQuery("attrValueList.name", attrGroup.getAttrName()));
                // 匹配商品规格参数值，这里多值匹配用【termsQuery】注意区分【termQuery】
                attrBoolQuery.filter(QueryBuilders.termsQuery("attrValueList.value", attrGroup.getAttrValues()));
                // 使用NestedQuery查询（嵌套对象的过滤查询）
                NestedQueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery("attrValueList", attrBoolQuery, ScoreMode.None);
                boolQueryBuilder.filter(nestedQueryBuilder);
            }
        }

        String keyword = query.getKeyword();
        if(StringUtils.isEmpty(keyword)){
            boolQueryBuilder.filter(QueryBuilders.matchAllQuery());
        }else{
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("name", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(10)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("subTitle", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(5)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("keywords", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("brandName", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("productCategoryName", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(3)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            boolQueryBuilder.filter(functionScoreQueryBuilder);
        }
        return boolQueryBuilder;
    }

    private SortBuilder addSort(QueryProduct query){
        int sort = 0;
        if(query.getSort()!=null){
            sort = query.getSort();
        }
        //return addSort(sort);
        SortBuilder builder = null;
        //排序
        if(sort==1){
            //按新品从新到旧
            builder = SortBuilders.fieldSort("id").order(SortOrder.DESC);
        }else if(sort==2){
            //按销量从高到低
            builder = SortBuilders.fieldSort("sale").order(SortOrder.DESC);
        }else if(sort==3){
            //按价格从低到高
            builder = SortBuilders.fieldSort("price").order(SortOrder.ASC);
        }else if(sort==4){
            //按价格从高到低
            builder = SortBuilders.fieldSort("price").order(SortOrder.DESC);
        }else{
            //按相关度
            builder = SortBuilders.scoreSort().order(SortOrder.DESC);
        }
        return builder;
    }

    /**
     * 将返回结果转换为对象
     */
    private AggProduct convertAggProduct(SearchResponse response){
        AggProduct aggVO = new AggProduct();

        Map<String, Aggregation> aggregationMap = response.getAggregations().getAsMap();
        AggProduct.ProductAttr attr = null;

        // 品牌聚合结果
        attr = new AggProduct.ProductAttr();
        attr.setAttrName("brandName");
        //Terms brandAgg = (Terms)search.getAggregation(BRAND_AGG);
        Terms brandAgg = (Terms)aggregationMap.get("brandNames");
        for (Terms.Bucket bucket : brandAgg.getBuckets()) {
            //String brandName = bucket.getKeyAsString();
            //aggVO.getBrandNames().add(brandName);
            attr.getAttrValues().add(new AggProduct.AttrValue(bucket.getKeyAsString(), bucket.getDocCount()));
        }
        aggVO.getProductProps().add(attr);

        // 分类聚合结果
        attr = new AggProduct.ProductAttr();
        attr.setAttrName("productCategoryName");
        //Terms categoryAgg = (Terms)search.getAggregation(CATEGORY_AGG);
        Terms categoryAgg = (Terms)aggregationMap.get("productCategoryNames");
        for (Terms.Bucket bucket : categoryAgg.getBuckets()) {
            attr.getAttrValues().add(new AggProduct.AttrValue(bucket.getKeyAsString(), bucket.getDocCount()));
        }
        aggVO.getProductProps().add(attr);

        // 规格参数聚合结果
        Aggregation productAttrs = aggregationMap.get("allAttrValues");
        List<LongTerms.Bucket> attrIds = ((LongTerms) ((InternalFilter) ((InternalNested) productAttrs).getProperty("productAttrs")).getProperty("attrIds")).getBuckets();
        for (Terms.Bucket attrId : attrIds) {
            attr = new AggProduct.ProductAttr();
            attr.setAttrId((Long) attrId.getKey());
            List<StringTerms.Bucket> attrValues = ((StringTerms) attrId.getAggregations().get("attrValues")).getBuckets();
            List<StringTerms.Bucket> attrNames = ((StringTerms) attrId.getAggregations().get("attrNames")).getBuckets();
            for (Terms.Bucket attrValue : attrValues) {
                AggProduct.AttrValue value = new AggProduct.AttrValue(attrValue.getKeyAsString(), attrValue.getDocCount());
                attr.getAttrValues().add(value);
            }
            if(!CollectionUtils.isEmpty(attrNames)){
                String attrName = attrNames.get(0).getKeyAsString();
                attr.setAttrName(attrName);
            }
            aggVO.getProductAttrs().add(attr);
        }
        return aggVO;
    }
}
