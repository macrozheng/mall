package com.macro.mall.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.mapper.UmsIntegrationExchangeRecordMapper;
import com.macro.mall.mapper.UmsPointMallCategoryMapper;
import com.macro.mall.mapper.UmsPointMallProductMapper;
import com.macro.mall.model.*;
import com.macro.mall.portal.domain.PointMallProductQueryParam;
import com.macro.mall.portal.service.UmsPointMallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class UmsPointMallServiceImpl implements UmsPointMallService {

    @Autowired
    private UmsPointMallProductMapper productMapper;
    @Autowired
    private UmsPointMallCategoryMapper categoryMapper;
    @Autowired
    private UmsIntegrationExchangeRecordMapper exchangeRecordMapper;

    @Override
    public boolean exchangeProduct(Long productId, Long memberId, Integer quantity) {
        // 检查商品是否存在
        UmsPointMallProduct product = productMapper.selectByPrimaryKey(productId);
        if (product == null || product.getStatus() != 1 || product.getStock() < quantity) {
            return false;
        }

        // 计算所需积分
        int requiredPoints = product.getPoint() * quantity;

        // TODO: 检查用户积分是否足够（需要调用积分服务）

        // 扣减库存
        product.setStock(product.getStock() - quantity);
        productMapper.updateByPrimaryKeySelective(product);

        // 记录兑换
        UmsIntegrationExchangeRecord record = new UmsIntegrationExchangeRecord();
        record.setMemberId(memberId);
        record.setProductId(productId);
        record.setProductName(product.getName());
        record.setQuantity(quantity);
        record.setPoint(requiredPoints);
        record.setStatus(0); // 待发货
        record.setCreateTime(new Date());
        exchangeRecordMapper.insert(record);

        return true;
    }

    @Override
    public List<UmsPointMallProduct> getProducts(PointMallProductQueryParam queryParam, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsPointMallProductExample example = new UmsPointMallProductExample();
        UmsPointMallProductExample.Criteria criteria = example.createCriteria();

        if (queryParam.getCategoryId() != null) {
            criteria.andCategoryIdEqualTo(queryParam.getCategoryId());
        }
        if (queryParam.getKeyword() != null) {
            criteria.andNameLike("%" + queryParam.getKeyword() + "%");
        }
        if (queryParam.getMinPoint() != null) {
            criteria.andPointGreaterThanOrEqualTo(queryParam.getMinPoint());
        }
        if (queryParam.getMaxPoint() != null) {
            criteria.andPointLessThanOrEqualTo(queryParam.getMaxPoint());
        }

        criteria.andStatusEqualTo(1); // 只显示上架商品
        example.setOrderByClause("sort desc, id desc");

        return productMapper.selectByExample(example);
    }

    @Override
    public List<UmsIntegrationExchangeRecord> getExchangeRecords(Long memberId, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsIntegrationExchangeRecordExample example = new UmsIntegrationExchangeRecordExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        example.setOrderByClause("create_time desc");

        List<UmsIntegrationExchangeRecord> records = exchangeRecordMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(records)) {
            for (UmsIntegrationExchangeRecord record : records) {
                UmsPointMallProduct product = productMapper.selectByPrimaryKey(record.getProductId());
                if (product != null) {
                    record.setProductName(product.getName());
                }
            }
        }

        return records;
    }

    @Override
    public List<UmsPointMallCategory> getCategories() {
        UmsPointMallCategoryExample example = new UmsPointMallCategoryExample();
        example.createCriteria().andStatusEqualTo(1);
        example.setOrderByClause("sort desc, id desc");
        return categoryMapper.selectByExample(example);
    }

    @Override
    public UmsPointMallProduct getProductById(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean cancelExchange(Long recordId, Long memberId) {
        UmsIntegrationExchangeRecord record = exchangeRecordMapper.selectByPrimaryKey(recordId);
        if (record == null || !record.getMemberId().equals(memberId) || record.getStatus() != 0) {
            return false;
        }

        // 恢复商品库存
        UmsPointMallProduct product = productMapper.selectByPrimaryKey(record.getProductId());
        if (product != null) {
            product.setStock(product.getStock() + record.getQuantity());
            productMapper.updateByPrimaryKeySelective(product);
        }

        // 更新兑换记录状态
        record.setStatus(3); // 已取消
        record.setUpdateTime(new Date());
        exchangeRecordMapper.updateByPrimaryKeySelective(record);

        // TODO: 退还积分

        return true;
    }

    @Override
    public boolean confirmReceive(Long recordId, Long memberId) {
        UmsIntegrationExchangeRecord record = exchangeRecordMapper.selectByPrimaryKey(recordId);
        if (record == null || !record.getMemberId().equals(memberId) || record.getStatus() != 1) {
            return false;
        }

        record.setStatus(2); // 已完成
        record.setUpdateTime(new Date());
        exchangeRecordMapper.updateByPrimaryKeySelective(record);

        return true;
    }

    @Override
    public List<UmsPointMallProduct> getHotProducts(int pageSize) {
        PageHelper.startPage(1, pageSize);
        UmsPointMallProductExample example = new UmsPointMallProductExample();
        example.createCriteria().andStatusEqualTo(1);
        example.setOrderByClause("exchange_count desc, id desc");
        return productMapper.selectByExample(example);
    }

    @Override
    public List<UmsPointMallProduct> getNewProducts(int pageSize) {
        PageHelper.startPage(1, pageSize);
        UmsPointMallProductExample example = new UmsPointMallProductExample();
        example.createCriteria().andStatusEqualTo(1);
        example.setOrderByClause("create_time desc, id desc");
        return productMapper.selectByExample(example);
    }

    @Override
    public List<UmsPointMallProduct> getRecommendProducts(int pageSize) {
        PageHelper.startPage(1, pageSize);
        UmsPointMallProductExample example = new UmsPointMallProductExample();
        example.createCriteria().andStatusEqualTo(1).andRecommendEqualTo(1);
        example.setOrderByClause("sort desc, id desc");
        return productMapper.selectByExample(example);
    }
}