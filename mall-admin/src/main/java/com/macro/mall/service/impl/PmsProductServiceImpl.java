package com.macro.mall.service.impl;

import com.macro.mall.dao.*;
import com.macro.mall.dto.PmsProductParam;
import com.macro.mall.mapper.PmsProductMapper;
import com.macro.mall.model.*;
import com.macro.mall.service.PmsProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 商品管理Service实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class PmsProductServiceImpl implements PmsProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductServiceImpl.class);
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private PmsMemberPriceDao memberPriceDao;
    @Autowired
    private PmsProductLadderDao productLadderDao;
    @Autowired
    private PmsProductFullReductionDao productFullReductionDao;
    @Autowired
    private PmsSkuStockDao skuStockDao;
    @Autowired
    private PmsProductAttributeValueDao productAttributeValueDao;
    @Autowired
    private CmsSubjectProductRelationDao subjectProductRelationDao;
    @Autowired
    private CmsPrefrenceAreaProductRelationDao prefrenceAreaProductRelationDao;

    @Override
    public int create(PmsProductParam productParam) {
        int count;
        //创建商品
        PmsProduct product = productParam.getProduct();
        product.setId(null);
        productMapper.insertSelective(product);
        //根据促销类型设置价格：、阶梯价格、满减价格
        Long productId = product.getId();
        //会员价格
        relateAndInsertList(memberPriceDao,productParam.getMemberPriceList(),productId);
        //阶梯价格
        relateAndInsertList(productLadderDao,productParam.getProductLadderList(),productId);
        //满减价格
        relateAndInsertList(productFullReductionDao, productParam.getProductFullReductionList(), productId);
        //添加sku库存信息
        relateAndInsertList(skuStockDao, productParam.getSkuStockList(), productId);
        //添加商品参数,添加自定义商品规格
        relateAndInsertList(productAttributeValueDao, productParam.getProductAttributeValueList(), productId);
        //关联专题
        relateAndInsertList(subjectProductRelationDao, productParam.getSubjectProductRelationList(), productId);
        //关联优选
        relateAndInsertList(prefrenceAreaProductRelationDao, productParam.getPrefrenceAreaProductRelationList(), productId);
        count = 1;
        return count;
    }

    /**
     * @deprecated
     * 旧版创建
     */
    public int createOld(PmsProductParam productParam) {
        int count;
        //创建商品
        PmsProduct product = productParam.getProduct();
        product.setId(null);
        productMapper.insertSelective(product);
        //根据促销类型设置价格：、阶梯价格、满减价格
        Long productId = product.getId();
        //会员价格
        List<PmsMemberPrice> memberPriceList = productParam.getMemberPriceList();
        if (!CollectionUtils.isEmpty(memberPriceList)) {
            for (PmsMemberPrice pmsMemberPrice : memberPriceList) {
                pmsMemberPrice.setId(null);
                pmsMemberPrice.setProductId(productId);
            }
            memberPriceDao.insertList(memberPriceList);
        }
        //阶梯价格
        List<PmsProductLadder> productLadderList = productParam.getProductLadderList();
        if (!CollectionUtils.isEmpty(productLadderList)) {
            for (PmsProductLadder productLadder : productLadderList) {
                productLadder.setId(null);
                productLadder.setProductId(productId);
            }
            productLadderDao.insertList(productLadderList);
        }
        //满减价格
        List<PmsProductFullReduction> productFullReductionList = productParam.getProductFullReductionList();
        if (!CollectionUtils.isEmpty(productFullReductionList)) {
            for (PmsProductFullReduction productFullReduction : productFullReductionList) {
                productFullReduction.setId(null);
                productFullReduction.setProductId(productId);
            }
            productFullReductionDao.insertList(productFullReductionList);
        }
        //添加sku库存信息
        List<PmsSkuStock> skuStockList = productParam.getSkuStockList();
        if(!CollectionUtils.isEmpty(skuStockList)){
            for (PmsSkuStock skuStock : skuStockList) {
                skuStock.setId(null);
                skuStock.setProductId(productId);
            }
            skuStockDao.insertList(skuStockList);
        }
        //添加商品参数,添加自定义商品规格
        List<PmsProductAttributeValue> productAttributeValueList = productParam.getProductAttributeValueList();
        if(!CollectionUtils.isEmpty(productAttributeValueList)){
            for (PmsProductAttributeValue productAttributeValue : productAttributeValueList) {
                productAttributeValue.setId(null);
                productAttributeValue.setProductId(productId);
            }
            productAttributeValueDao.insertList(productAttributeValueList);
        }
        //关联专题
        relateAndInsertList(subjectProductRelationDao, productParam.getSubjectProductRelationList(), productId);
        //关联优选
        relateAndInsertList(prefrenceAreaProductRelationDao, productParam.getPrefrenceAreaProductRelationList(), productId);
        count = 1;
        return count;
    }

    /**
     * 建立和插入关系表操作
     *
     * @param dao       可以操作的dao
     * @param dataList  要插入的数据
     * @param productId 建立关系的id
     */
    private void relateAndInsertList(Object dao, List dataList, Long productId) {
        try {
            if (CollectionUtils.isEmpty(dataList)) return;
            for (Object item : dataList) {
                Method setId = item.getClass().getMethod("setId", Long.class);
                setId.invoke(item, (Long) null);
                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(item, productId);
            }
            Method insertList = dao.getClass().getMethod("insertList", List.class);
            insertList.invoke(dao, dataList);
        } catch (Exception e) {
            LOGGER.warn("创建产品出错:{}",e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
