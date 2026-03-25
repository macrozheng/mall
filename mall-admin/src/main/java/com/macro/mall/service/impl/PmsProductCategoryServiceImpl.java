package com.macro.mall.service.impl;

import com.macro.mall.common.util.SpecificationBuilder;
import com.macro.mall.dto.PmsProductCategoryParam;
import com.macro.mall.dto.PmsProductCategoryWithChildrenItem;
import com.macro.mall.repository.PmsProductCategoryAttributeRelationRepository;
import com.macro.mall.repository.PmsProductCategoryRepository;
import com.macro.mall.repository.PmsProductRepository;
import com.macro.mall.model.*;
import com.macro.mall.service.PmsProductCategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类管理Service实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService {
    @Autowired
    private PmsProductCategoryRepository productCategoryRepository;
    @Autowired
    private PmsProductRepository productRepository;
    @Autowired
    private PmsProductCategoryAttributeRelationRepository productCategoryAttributeRelationRepository;

    @Override
    public int create(PmsProductCategoryParam pmsProductCategoryParam) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setProductCount(0);
        BeanUtils.copyProperties(pmsProductCategoryParam, productCategory);
        //没有父分类时为一级分类
        setCategoryLevel(productCategory);
        productCategoryRepository.save(productCategory);
        //创建筛选属性关联
        List<Long> productAttributeIdList = pmsProductCategoryParam.getProductAttributeIdList();
        if(!CollectionUtils.isEmpty(productAttributeIdList)){
            insertRelationList(productCategory.getId(), productAttributeIdList);
        }
        return 1;
    }

    /**
     * 批量插入商品分类与筛选属性关系表
     * @param productCategoryId 商品分类id
     * @param productAttributeIdList 相关商品筛选属性id集合
     */
    private void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
        List<PmsProductCategoryAttributeRelation> relationList = new ArrayList<>();
        for (Long productAttrId : productAttributeIdList) {
            PmsProductCategoryAttributeRelation relation = new PmsProductCategoryAttributeRelation();
            relation.setProductAttributeId(productAttrId);
            relation.setProductCategoryId(productCategoryId);
            relationList.add(relation);
        }
        productCategoryAttributeRelationRepository.saveAll(relationList);
    }

    @Override
    public int update(Long id, PmsProductCategoryParam pmsProductCategoryParam) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setId(id);
        BeanUtils.copyProperties(pmsProductCategoryParam, productCategory);
        setCategoryLevel(productCategory);
        //更新商品分类时要更新商品中的名称
        updateProductCategoryName(id, productCategory.getName());
        //同时更新筛选属性的信息
        deleteRelationByCategoryId(id);
        if(!CollectionUtils.isEmpty(pmsProductCategoryParam.getProductAttributeIdList())){
            insertRelationList(id,pmsProductCategoryParam.getProductAttributeIdList());
        }
        productCategoryRepository.save(productCategory);
        return 1;
    }

    private void updateProductCategoryName(Long categoryId, String categoryName) {
        SpecificationBuilder<PmsProduct> builder = SpecificationBuilder.create();
        builder.eq("productCategoryId", categoryId);
        List<PmsProduct> products = productRepository.findAll(builder.build());
        for (PmsProduct product : products) {
            product.setProductCategoryName(categoryName);
        }
        productRepository.saveAll(products);
    }

    private void deleteRelationByCategoryId(Long categoryId) {
        SpecificationBuilder<PmsProductCategoryAttributeRelation> builder = SpecificationBuilder.create();
        builder.eq("productCategoryId", categoryId);
        List<PmsProductCategoryAttributeRelation> relations = productCategoryAttributeRelationRepository.findAll(builder.build());
        if (CollectionUtils.isNotEmpty(relations)) {
            productCategoryAttributeRelationRepository.deleteAll(relations);
        }
    }

    @Override
    public List<PmsProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum) {
        SpecificationBuilder<PmsProductCategory> builder = SpecificationBuilder.create();
        if (parentId != null) {
            builder.eq("parentId", parentId);
        }
        return productCategoryRepository.findAll(builder.build());
    }

    @Override
    public int delete(Long id) {
        productCategoryRepository.deleteById(id);
        return 1;
    }

    @Override
    public PmsProductCategory getItem(Long id) {
        return productCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public int updateNavStatus(List<Long> ids, Integer navStatus) {
        List<PmsProductCategory> categories = productCategoryRepository.findAllById(ids);
        for (PmsProductCategory category : categories) {
            category.setNavStatus(navStatus);
        }
        productCategoryRepository.saveAll(categories);
        return ids.size();
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        List<PmsProductCategory> categories = productCategoryRepository.findAllById(ids);
        for (PmsProductCategory category : categories) {
            category.setShowStatus(showStatus);
        }
        productCategoryRepository.saveAll(categories);
        return ids.size();
    }

    @Override
    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
        // TODO: 实现带子分类的查询
        return new ArrayList<>();
    }

    /**
     * 根据分类的parentId设置分类的level
     */
    private void setCategoryLevel(PmsProductCategory productCategory) {
        //没有父分类时为一级分类
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            //有父分类时选择根据父分类level设置
            PmsProductCategory parentCategory = productCategoryRepository.findById(productCategory.getParentId()).orElse(null);
            if (parentCategory != null) {
                productCategory.setLevel(parentCategory.getLevel() + 1);
            } else {
                productCategory.setLevel(0);
            }
        }
    }
}
