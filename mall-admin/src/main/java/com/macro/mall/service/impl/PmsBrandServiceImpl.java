package com.macro.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.macro.mall.dto.PmsBrandParam;
import com.macro.mall.model.PmsBrand;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.repository.PmsBrandRepository;
import com.macro.mall.repository.PmsProductRepository;
import com.macro.mall.service.PmsBrandService;
import com.macro.mall.common.util.SpecificationBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品品牌管理Service实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Autowired
    private PmsBrandRepository brandRepository;
    @Autowired
    private PmsProductRepository productRepository;

    @Override
    public List<PmsBrand> listAllBrand() {
        return brandRepository.findAll();
    }

    @Override
    public int createBrand(PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StrUtil.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        PmsBrand saved = brandRepository.save(pmsBrand);
        return saved != null ? 1 : 0;
    }

    @Override
    public int updateBrand(Long id, PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        pmsBrand.setId(id);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StrUtil.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        //更新品牌时要更新商品中的品牌名称
        List<PmsProduct> products = productRepository.findAll(
            SpecificationBuilder.<PmsProduct>create().eq("brandId", id).build()
        );
        for (PmsProduct product : products) {
            product.setBrandName(pmsBrand.getName());
            productRepository.save(product);
        }
        PmsBrand saved = brandRepository.save(pmsBrand);
        return saved != null ? 1 : 0;
    }

    @Override
    public int deleteBrand(Long id) {
        brandRepository.deleteById(id);
        return 1;
    }

    @Override
    public int deleteBrand(List<Long> ids) {
        List<PmsBrand> brands = brandRepository.findAllById(ids);
        brandRepository.deleteAll(brands);
        return brands.size();
    }

    @Override
    public List<PmsBrand> listBrand(String keyword, Integer showStatus, int pageNum, int pageSize) {
        SpecificationBuilder<PmsBrand> builder = SpecificationBuilder.create();
        if (StrUtil.isNotEmpty(keyword)) {
            builder.like("name", keyword);
        }
        if (showStatus != null) {
            builder.eq("showStatus", showStatus);
        }
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.DESC, "sort"));
        Page<PmsBrand> page = brandRepository.findAll(builder.build(), pageRequest);
        return page.getContent();
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        List<PmsBrand> brands = brandRepository.findAllById(ids);
        for (PmsBrand brand : brands) {
            brand.setShowStatus(showStatus);
        }
        brandRepository.saveAll(brands);
        return brands.size();
    }

    @Override
    public int updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        List<PmsBrand> brands = brandRepository.findAllById(ids);
        for (PmsBrand brand : brands) {
            brand.setFactoryStatus(factoryStatus);
        }
        brandRepository.saveAll(brands);
        return brands.size();
    }
}
