package com.macro.mall.service.impl;

import com.macro.mall.common.util.SpecificationBuilder;
import com.macro.mall.dto.PmsProductAttributeParam;
import com.macro.mall.dto.ProductAttrInfo;
import com.macro.mall.repository.PmsProductAttributeCategoryRepository;
import com.macro.mall.repository.PmsProductAttributeRepository;
import com.macro.mall.model.PmsProductAttribute;
import com.macro.mall.model.PmsProductAttributeCategory;
import com.macro.mall.service.PmsProductAttributeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品属性管理Service实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class PmsProductAttributeServiceImpl implements PmsProductAttributeService {
    @Autowired
    private PmsProductAttributeCategoryRepository productAttributeCategoryRepository;
    @Autowired
    private PmsProductAttributeRepository productAttributeRepository;

    @Override
    public List<PmsProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        SpecificationBuilder<PmsProductAttribute> builder = SpecificationBuilder.create();
        if (cid != null) {
            builder.eq("productAttributeCategoryId", cid);
        }
        if (type != null) {
            builder.eq("type", type);
        }
        return productAttributeRepository.findAll(builder.build());
    }

    @Override
    public int create(PmsProductAttributeParam pmsProductAttributeParam) {
        PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();
        BeanUtils.copyProperties(pmsProductAttributeParam, pmsProductAttribute);
        productAttributeRepository.save(pmsProductAttribute);
        //新增商品属性以后需要更新商品属性分类数量
        PmsProductAttributeCategory pmsProductAttributeCategory = productAttributeCategoryRepository.findById(pmsProductAttribute.getProductAttributeCategoryId()).orElse(null);
        if(pmsProductAttributeCategory != null){
            if(pmsProductAttribute.getType()==0){
                pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount()+1);
            }else if(pmsProductAttribute.getType()==1){
                pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount()+1);
            }
            productAttributeCategoryRepository.save(pmsProductAttributeCategory);
        }
        return 1;
    }

    @Override
    public int update(Long id, PmsProductAttributeParam productAttributeParam) {
        PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();
        pmsProductAttribute.setId(id);
        BeanUtils.copyProperties(productAttributeParam, pmsProductAttribute);
        productAttributeRepository.save(pmsProductAttribute);
        return 1;
    }

    @Override
    public PmsProductAttribute getItem(Long id) {
        return productAttributeRepository.findById(id).orElse(null);
    }

    @Override
    public int delete(List<Long> ids) {
        //获取分类
        PmsProductAttribute pmsProductAttribute = productAttributeRepository.findById(ids.get(0)).orElse(null);
        if (pmsProductAttribute == null) {
            return 0;
        }
        Integer type = pmsProductAttribute.getType();
        PmsProductAttributeCategory pmsProductAttributeCategory = productAttributeCategoryRepository.findById(pmsProductAttribute.getProductAttributeCategoryId()).orElse(null);
        //删除属性
        List<PmsProductAttribute> attributes = productAttributeRepository.findAllById(ids);
        productAttributeRepository.deleteAll(attributes);
        int count = ids.size();
        //删除完成后修改数量
        if(pmsProductAttributeCategory != null){
            if(type==0){
                if(pmsProductAttributeCategory.getAttributeCount()>=count){
                    pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount()-count);
                }else{
                    pmsProductAttributeCategory.setAttributeCount(0);
                }
            }else if(type==1){
                if(pmsProductAttributeCategory.getParamCount()>=count){
                    pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount()-count);
                }else{
                    pmsProductAttributeCategory.setParamCount(0);
                }
            }
            productAttributeCategoryRepository.save(pmsProductAttributeCategory);
        }
        return count;
    }

    @Override
    public List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId) {
        // TODO: 实现商品属性信息查询
        return new java.util.ArrayList<>();
    }
}
