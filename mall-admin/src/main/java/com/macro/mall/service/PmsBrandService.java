package com.macro.mall.service;

import com.macro.mall.dto.PmsBrandDto;
import com.macro.mall.model.PmsBrand;

import java.util.List;

/**
 * 商品品牌Service
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrandDto pmsBrandDto);

    int updateBrand(Long id, PmsBrandDto pmsBrandDto);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
