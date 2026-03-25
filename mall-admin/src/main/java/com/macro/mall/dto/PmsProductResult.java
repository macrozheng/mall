package com.macro.mall.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 查询单个商品修改后返回的结果
 * Created by macro on 2018/4/26.
 */
public class PmsProductResult extends PmsProductParam {
    @Getter
    @Setter
    private Long cateParentId;
}
