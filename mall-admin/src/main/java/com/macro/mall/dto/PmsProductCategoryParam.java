package com.macro.mall.dto;

import com.macro.mall.validator.FlagValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 添加更新商品分类的参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode
public class PmsProductCategoryParam {
    private Long parentId;
    @NotEmpty
    private String name;
    private String productUnit;
    @FlagValidator(value = {"0","1"},message = "状态只能为0或1")
    private Integer navStatus;
    @FlagValidator(value = {"0","1"},message = "状态只能为0或1")
    private Integer showStatus;
    @Min(value = 0)
    private Integer sort;
    private String icon;
    private String keywords;
    private String description;
    private List<Long> productAttributeIdList;
}
