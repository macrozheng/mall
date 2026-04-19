package com.macro.mall.service.impl;

import com.macro.mall.common.exception.Asserts;
import com.macro.mall.dao.SmsGroupBuyActivityDao;
import com.macro.mall.dto.SmsGroupBuyProductParam;
import com.macro.mall.mapper.SmsGroupBuyProductMapper;
import com.macro.mall.model.SmsGroupBuyProduct;
import com.macro.mall.service.SmsGroupBuyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 拼团活动商品Service实现
 */
@Service
public class SmsGroupBuyProductServiceImpl implements SmsGroupBuyProductService {

    @Autowired
    private SmsGroupBuyProductMapper productMapper;

    @Autowired
    private SmsGroupBuyActivityDao activityDao;

    @Override
    @Transactional
    public int createBatch(SmsGroupBuyProductParam param) {
        if (param == null || param.getActivityId() == null
                || param.getProductList() == null || param.getProductList().isEmpty()) {
            Asserts.fail("参数不完整");
        }
        int count = 0;
        for (SmsGroupBuyProduct product : param.getProductList()) {
            product.setActivityId(param.getActivityId());
            if (product.getLockedStock() == null) product.setLockedStock(0);
            if (product.getSoldCount() == null) product.setSoldCount(0);
            if (product.getLimitPerOrder() == null) product.setLimitPerOrder(1);
            if (product.getSort() == null) product.setSort(0);
            count += productMapper.insertSelective(product);
        }
        return count;
    }

    @Override
    public int update(Long id, SmsGroupBuyProduct product) {
        product.setId(id);
        // 关键字段安全防护:活动ID、已锁定库存、已售数量不允许通过此接口修改
        product.setActivityId(null);
        product.setLockedStock(null);
        product.setSoldCount(null);
        return productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public int delete(Long id) {
        SmsGroupBuyProduct product = productMapper.selectByPrimaryKey(id);
        if (product != null && product.getLockedStock() != null && product.getLockedStock() > 0) {
            Asserts.fail("该商品存在进行中的团占用库存,不可删除");
        }
        return productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SmsGroupBuyProduct> listByActivity(Long activityId) {
        return activityDao.listProductByActivity(activityId);
    }
}
