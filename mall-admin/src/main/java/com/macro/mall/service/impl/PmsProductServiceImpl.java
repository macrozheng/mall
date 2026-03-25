package com.macro.mall.service.impl;

import com.macro.mall.common.util.SpecificationBuilder;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.macro.mall.dto.PmsProductParam;
import com.macro.mall.dto.PmsProductQueryParam;
import com.macro.mall.dto.PmsProductResult;
import com.macro.mall.model.*;
import com.macro.mall.repository.*;
import com.macro.mall.service.PmsProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品管理Service实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class PmsProductServiceImpl implements PmsProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductServiceImpl.class);
    
    @Autowired
    private PmsProductRepository productRepository;
    @Autowired
    private PmsProductLadderRepository productLadderRepository;
    @Autowired
    private PmsProductFullReductionRepository productFullReductionRepository;
    @Autowired
    private PmsSkuStockRepository skuStockRepository;
    @Autowired
    private PmsProductAttributeValueRepository productAttributeValueRepository;
    @Autowired
    private CmsSubjectProductRelationRepository subjectProductRelationRepository;
    @Autowired
    private CmsPrefrenceAreaProductRelationRepository prefrenceAreaProductRelationRepository;
    @Autowired
    private PmsProductVertifyRecordRepository productVertifyRecordRepository;
    @Autowired
    private PmsMemberPriceRepository memberPriceRepository;

    @Override
    public int create(PmsProductParam productParam) {
        //创建商品
        PmsProduct product = productParam;
        product.setId(null);
        productRepository.save(product);
        //根据促销类型设置价格：会员价格、阶梯价格、满减价格
        Long productId = product.getId();
        //会员价格
        saveList(memberPriceRepository, productParam.getMemberPriceList(), productId);
        //阶梯价格
        saveList(productLadderRepository, productParam.getProductLadderList(), productId);
        //满减价格
        saveList(productFullReductionRepository, productParam.getProductFullReductionList(), productId);
        //处理sku的编码
        handleSkuStockCode(productParam.getSkuStockList(), productId);
        //添加sku库存信息
        saveList(skuStockRepository, productParam.getSkuStockList(), productId);
        //添加商品参数,添加自定义商品规格
        saveList(productAttributeValueRepository, productParam.getProductAttributeValueList(), productId);
        //关联专题
        saveList(subjectProductRelationRepository, productParam.getSubjectProductRelationList(), productId);
        //关联优选
        saveList(prefrenceAreaProductRelationRepository, productParam.getPrefrenceAreaProductRelationList(), productId);
        return 1;
    }

    private void handleSkuStockCode(List<PmsSkuStock> skuStockList, Long productId) {
        if (CollectionUtils.isEmpty(skuStockList)) return;
        for (int i = 0; i < skuStockList.size(); i++) {
            PmsSkuStock skuStock = skuStockList.get(i);
            if (StrUtil.isEmpty(skuStock.getSkuCode())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", productId));
                //三位索引id
                sb.append(String.format("%03d", i + 1));
                skuStock.setSkuCode(sb.toString());
            }
        }
    }

    @Override
    public PmsProductResult getUpdateInfo(Long id) {
        // 简化实现，返回基本信息
        PmsProduct product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        PmsProductResult result = new PmsProductResult();
        // 复制基本属性
        result.setId(product.getId());
        result.setName(product.getName());
        result.setProductSn(product.getProductSn());
        result.setBrandId(product.getBrandId());
        result.setProductCategoryId(product.getProductCategoryId());
        // 查询关联信息
        SpecificationBuilder<PmsMemberPrice> memberPriceBuilder = SpecificationBuilder.create();
        memberPriceBuilder.eq("productId", id);
        result.setMemberPriceList(memberPriceRepository.findAll(memberPriceBuilder.build()));
        
        SpecificationBuilder<PmsProductLadder> ladderBuilder = SpecificationBuilder.create();
        ladderBuilder.eq("productId", id);
        result.setProductLadderList(productLadderRepository.findAll(ladderBuilder.build()));
        
        SpecificationBuilder<PmsProductFullReduction> reductionBuilder = SpecificationBuilder.create();
        reductionBuilder.eq("productId", id);
        result.setProductFullReductionList(productFullReductionRepository.findAll(reductionBuilder.build()));
        
        SpecificationBuilder<PmsSkuStock> skuBuilder = SpecificationBuilder.create();
        skuBuilder.eq("productId", id);
        result.setSkuStockList(skuStockRepository.findAll(skuBuilder.build()));
        
        SpecificationBuilder<PmsProductAttributeValue> attrBuilder = SpecificationBuilder.create();
        attrBuilder.eq("productId", id);
        result.setProductAttributeValueList(productAttributeValueRepository.findAll(attrBuilder.build()));
        
        SpecificationBuilder<CmsSubjectProductRelation> subjectBuilder = SpecificationBuilder.create();
        subjectBuilder.eq("productId", id);
        result.setSubjectProductRelationList(subjectProductRelationRepository.findAll(subjectBuilder.build()));
        
        SpecificationBuilder<CmsPrefrenceAreaProductRelation> areaBuilder = SpecificationBuilder.create();
        areaBuilder.eq("productId", id);
        result.setPrefrenceAreaProductRelationList(prefrenceAreaProductRelationRepository.findAll(areaBuilder.build()));
        
        return result;
    }

    @Override
    public int update(Long id, PmsProductParam productParam) {
        //更新商品信息
        PmsProduct product = productParam;
        product.setId(id);
        productRepository.save(product);
        //会员价格
        deleteByProductId(memberPriceRepository, id);
        saveList(memberPriceRepository, productParam.getMemberPriceList(), id);
        //阶梯价格
        deleteByProductId(productLadderRepository, id);
        saveList(productLadderRepository, productParam.getProductLadderList(), id);
        //满减价格
        deleteByProductId(productFullReductionRepository, id);
        saveList(productFullReductionRepository, productParam.getProductFullReductionList(), id);
        //修改sku库存信息
        handleUpdateSkuStockList(id, productParam);
        //修改商品参数,添加自定义商品规格
        deleteByProductId(productAttributeValueRepository, id);
        saveList(productAttributeValueRepository, productParam.getProductAttributeValueList(), id);
        //关联专题
        deleteByProductId(subjectProductRelationRepository, id);
        saveList(subjectProductRelationRepository, productParam.getSubjectProductRelationList(), id);
        //关联优选
        deleteByProductId(prefrenceAreaProductRelationRepository, id);
        saveList(prefrenceAreaProductRelationRepository, productParam.getPrefrenceAreaProductRelationList(), id);
        return 1;
    }

    private <T> void deleteByProductId(JpaSpecificationExecutor<T> repository, Long productId) {
        SpecificationBuilder<T> builder = SpecificationBuilder.create();
        builder.eq("productId", productId);
        List<T> list = repository.findAll(builder.build());
        if (CollUtil.isNotEmpty(list)) {
            ((JpaRepository<T, Long>) repository).deleteAll(list);
        }
    }

    private void handleUpdateSkuStockList(Long id, PmsProductParam productParam) {
        //当前的sku信息
        List<PmsSkuStock> currSkuList = productParam.getSkuStockList();
        //获取初始sku信息
        SpecificationBuilder<PmsSkuStock> skuBuilder = SpecificationBuilder.create();
        skuBuilder.eq("productId", id);
        List<PmsSkuStock> oriStuList = skuStockRepository.findAll(skuBuilder.build());
        
        //当前没有sku直接删除
        if (CollUtil.isEmpty(currSkuList)) {
            skuStockRepository.deleteAll(oriStuList);
            return;
        }
        //获取新增sku信息
        List<PmsSkuStock> insertSkuList = currSkuList.stream().filter(item -> item.getId() == null).collect(Collectors.toList());
        //获取需要更新的sku信息
        List<PmsSkuStock> updateSkuList = currSkuList.stream().filter(item -> item.getId() != null).collect(Collectors.toList());
        List<Long> updateSkuIds = updateSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());
        //获取需要删除的sku信息
        List<PmsSkuStock> removeSkuList = oriStuList.stream().filter(item -> !updateSkuIds.contains(item.getId())).collect(Collectors.toList());
        handleSkuStockCode(insertSkuList, id);
        handleSkuStockCode(updateSkuList, id);
        //新增sku
        if (CollUtil.isNotEmpty(insertSkuList)) {
            setProductIdAndSave(skuStockRepository, insertSkuList, id);
        }
        //删除sku
        if (CollUtil.isNotEmpty(removeSkuList)) {
            skuStockRepository.deleteAll(removeSkuList);
        }
        //修改sku
        if (CollUtil.isNotEmpty(updateSkuList)) {
            skuStockRepository.saveAll(updateSkuList);
        }
    }

    @Override
    public List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum) {
        SpecificationBuilder<PmsProduct> builder = SpecificationBuilder.create();
        builder.eq("deleteStatus", 0);
        if (productQueryParam.getPublishStatus() != null) {
            builder.eq("publishStatus", productQueryParam.getPublishStatus());
        }
        if (productQueryParam.getVerifyStatus() != null) {
            builder.eq("verifyStatus", productQueryParam.getVerifyStatus());
        }
        if (!StrUtil.isEmpty(productQueryParam.getKeyword())) {
            builder.like("name", productQueryParam.getKeyword());
        }
        if (!StrUtil.isEmpty(productQueryParam.getProductSn())) {
            builder.eq("productSn", productQueryParam.getProductSn());
        }
        if (productQueryParam.getBrandId() != null) {
            builder.eq("brandId", productQueryParam.getBrandId());
        }
        if (productQueryParam.getProductCategoryId() != null) {
            builder.eq("productCategoryId", productQueryParam.getProductCategoryId());
        }
        return productRepository.findAll(builder.build());
    }

    @Override
    public int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail) {
        List<PmsProduct> products = productRepository.findAllById(ids);
        for (PmsProduct product : products) {
            product.setVerifyStatus(verifyStatus);
        }
        productRepository.saveAll(products);
        //修改完审核状态后插入审核记录
        List<PmsProductVertifyRecord> list = new ArrayList<>();
        for (Long id : ids) {
            PmsProductVertifyRecord record = new PmsProductVertifyRecord();
            record.setProductId(id);
            record.setCreateTime(new Date());
            record.setDetail(detail);
            record.setStatus(verifyStatus);
            record.setVertifyMan("test");
            list.add(record);
        }
        productVertifyRecordRepository.saveAll(list);
        return ids.size();
    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        List<PmsProduct> products = productRepository.findAllById(ids);
        for (PmsProduct product : products) {
            product.setPublishStatus(publishStatus);
        }
        productRepository.saveAll(products);
        return ids.size();
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        List<PmsProduct> products = productRepository.findAllById(ids);
        for (PmsProduct product : products) {
            product.setRecommandStatus(recommendStatus);
        }
        productRepository.saveAll(products);
        return ids.size();
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        List<PmsProduct> products = productRepository.findAllById(ids);
        for (PmsProduct product : products) {
            product.setNewStatus(newStatus);
        }
        productRepository.saveAll(products);
        return ids.size();
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        List<PmsProduct> products = productRepository.findAllById(ids);
        for (PmsProduct product : products) {
            product.setDeleteStatus(deleteStatus);
        }
        productRepository.saveAll(products);
        return ids.size();
    }

    @Override
    public List<PmsProduct> list(String keyword) {
        SpecificationBuilder<PmsProduct> builder = SpecificationBuilder.create();
        builder.eq("deleteStatus", 0);
        if (!StrUtil.isEmpty(keyword)) {
            builder.like("name", keyword);
        }
        return productRepository.findAll(builder.build());
    }

    /**
     * 保存列表并设置productId
     */
    private <T> void saveList(JpaRepository<T, Long> repository, List<T> dataList, Long productId) {
        if (CollectionUtils.isEmpty(dataList)) return;
        for (Object item : dataList) {
            try {
                item.getClass().getMethod("setId", Long.class).invoke(item, (Long) null);
                item.getClass().getMethod("setProductId", Long.class).invoke(item, productId);
            } catch (Exception e) {
                LOGGER.warn("设置productId出错:{}", e.getMessage());
            }
        }
        repository.saveAll(dataList);
    }

    /**
     * 设置productId并保存
     */
    private <T> void setProductIdAndSave(JpaRepository<T, Long> repository, List<T> dataList, Long productId) {
        if (CollectionUtils.isEmpty(dataList)) return;
        for (Object item : dataList) {
            try {
                item.getClass().getMethod("setProductId", Long.class).invoke(item, productId);
            } catch (Exception e) {
                LOGGER.warn("设置productId出错:{}", e.getMessage());
            }
        }
        repository.saveAll(dataList);
    }
}
