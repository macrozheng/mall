package com.macro.mall.service.impl;


import com.macro.mall.common.util.SpecificationBuilder;
import cn.hutool.core.util.StrUtil;
import com.macro.mall.dto.SmsCouponParam;
import com.macro.mall.repository.SmsCouponRepository;
import com.macro.mall.repository.SmsCouponProductCategoryRelationRepository;
import com.macro.mall.repository.SmsCouponProductRelationRepository;
import com.macro.mall.model.*;
import com.macro.mall.service.SmsCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优惠券管理Service实现类
 * Created by macro on 2018/8/28.
 */
@Service
public class SmsCouponServiceImpl implements SmsCouponService {
    @Autowired
    private SmsCouponRepository couponRepository;
    @Autowired
    private SmsCouponProductCategoryRelationRepository productCategoryRelationRepository;
    @Autowired
    private SmsCouponProductRelationRepository productRelationRepository;

    @Override
    public int create(SmsCouponParam couponParam) {
        couponParam.setCount(couponParam.getPublishCount());
        couponParam.setUseCount(0);
        couponParam.setReceiveCount(0);
        //插入优惠券表
        couponRepository.save(couponParam);
        int count = 1;
        //插入优惠券和商品关系表
        if(couponParam.getUseType().equals(2)){
            for(SmsCouponProductRelation productRelation:couponParam.getProductRelationList()){
                productRelation.setCouponId(couponParam.getId());
            }
            productRelationRepository.saveAll(couponParam.getProductRelationList());
        }
        //插入优惠券和商品分类关系表
        if(couponParam.getUseType().equals(1)){
            for (SmsCouponProductCategoryRelation couponProductCategoryRelation : couponParam.getProductCategoryRelationList()) {
                couponProductCategoryRelation.setCouponId(couponParam.getId());
            }
            productCategoryRelationRepository.saveAll(couponParam.getProductCategoryRelationList());
        }
        return count;
    }

    @Override
    public int delete(Long id) {
        //删除优惠券
        couponRepository.deleteById(id);
        //删除商品关联
        deleteProductRelation(id);
        //删除商品分类关联
        deleteProductCategoryRelation(id);
        return 1;
    }

    private void deleteProductCategoryRelation(Long couponId) {
        SpecificationBuilder<SmsCouponProductCategoryRelation> builder = SpecificationBuilder.create();
        builder.eq("couponId", couponId);
        productCategoryRelationRepository.deleteAll(productCategoryRelationRepository.findAll(builder.build()));
    }

    private void deleteProductRelation(Long couponId) {
        SpecificationBuilder<SmsCouponProductRelation> builder = SpecificationBuilder.create();
        builder.eq("couponId", couponId);
        productRelationRepository.deleteAll(productRelationRepository.findAll(builder.build()));
    }

    @Override
    public int update(Long id, SmsCouponParam couponParam) {
        couponParam.setId(id);
        couponRepository.save(couponParam);
        int count = 1;
        //删除后插入优惠券和商品关系表
        if(couponParam.getUseType().equals(2)){
            for(SmsCouponProductRelation productRelation:couponParam.getProductRelationList()){
                productRelation.setCouponId(couponParam.getId());
            }
            deleteProductRelation(id);
            productRelationRepository.saveAll(couponParam.getProductRelationList());
        }
        //删除后插入优惠券和商品分类关系表
        if(couponParam.getUseType().equals(1)){
            for (SmsCouponProductCategoryRelation couponProductCategoryRelation : couponParam.getProductCategoryRelationList()) {
                couponProductCategoryRelation.setCouponId(couponParam.getId());
            }
            deleteProductCategoryRelation(id);
            productCategoryRelationRepository.saveAll(couponParam.getProductCategoryRelationList());
        }
        return count;
    }

    @Override
    public List<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum) {
        SpecificationBuilder<SmsCoupon> builder = SpecificationBuilder.create();
        if(!StrUtil.isEmpty(name)){
            builder.like("name", name);
        }
        if(type!=null){
            builder.eq("type", type);
        }
        return couponRepository.findAll(builder.build());
    }

    @Override
    public SmsCouponParam getItem(Long id) {
        SmsCouponParam couponParam = new SmsCouponParam();
        SmsCoupon coupon = couponRepository.findById(id).orElse(null);
        if (coupon == null) {
            return null;
        }
        // 复制基本属性
        couponParam.setId(coupon.getId());
        couponParam.setName(coupon.getName());
        couponParam.setType(coupon.getType());
        couponParam.setPlatform(coupon.getPlatform());
        couponParam.setCount(coupon.getCount());
        couponParam.setAmount(coupon.getAmount());
        couponParam.setPerLimit(coupon.getPerLimit());
        couponParam.setMinPoint(coupon.getMinPoint());
        couponParam.setStartTime(coupon.getStartTime());
        couponParam.setEndTime(coupon.getEndTime());
        couponParam.setUseType(coupon.getUseType());
        couponParam.setNote(coupon.getNote());
        couponParam.setPublishCount(coupon.getPublishCount());
        couponParam.setUseCount(coupon.getUseCount());
        couponParam.setReceiveCount(coupon.getReceiveCount());
        couponParam.setEnableTime(coupon.getEnableTime());
        couponParam.setCode(coupon.getCode());
        couponParam.setMemberLevel(coupon.getMemberLevel());
        // 查询商品关联
        SpecificationBuilder<SmsCouponProductRelation> productBuilder = SpecificationBuilder.create();
        productBuilder.eq("couponId", id);
        couponParam.setProductRelationList(productRelationRepository.findAll(productBuilder.build()));
        // 查询商品分类关联
        SpecificationBuilder<SmsCouponProductCategoryRelation> categoryBuilder = SpecificationBuilder.create();
        categoryBuilder.eq("couponId", id);
        couponParam.setProductCategoryRelationList(productCategoryRelationRepository.findAll(categoryBuilder.build()));
        return couponParam;
    }
}
