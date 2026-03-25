package com.macro.mall.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品查询参数
 * Created by macro on 2018/4/27.
 */
@Data
@EqualsAndHashCode
public class PmsProductQueryParam {
    private Integer publishStatus;
    private Integer verifyStatus;
    private String keyword;
    private String productSn;
    private Long productCategoryId;
    private Long brandId;
}
