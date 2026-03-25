package com.macro.mall.dto;

import com.macro.mall.validator.FlagValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotEmpty;

/**
 * 商品属性参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode
public class PmsProductAttributeParam {
    @NotEmpty
    private Long productAttributeCategoryId;
    @NotEmpty
    private String name;
    @FlagValidator({"0","1","2"})
    private Integer selectType;
    @FlagValidator({"0","1"})
    private Integer inputType;
    private String inputList;
    private Integer sort;
    @FlagValidator({"0","1"})
    private Integer filterType;
    @FlagValidator({"0","1","2"})
    private Integer searchType;
    @FlagValidator({"0","1"})
    private Integer relatedStatus;
    @FlagValidator({"0","1"})
    private Integer handAddStatus;
    @FlagValidator({"0","1"})
    private Integer type;
}
