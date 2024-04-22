package com.macro.mall.dto;

import com.macro.mall.validator.FlagValidator;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(title = "属性分类ID")
    private Long productAttributeCategoryId;
    @NotEmpty
    @Schema(title = "属性名称")
    private String name;
    @FlagValidator({"0","1","2"})
    @Schema(title = "属性选择类型：0->唯一；1->单选；2->多选")
    private Integer selectType;
    @FlagValidator({"0","1"})
    @Schema(title = "属性录入方式：0->手工录入；1->从列表中选取")
    private Integer inputType;
    @Schema(title = "可选值列表，以逗号隔开")
    private String inputList;
    private Integer sort;
    @Schema(title = "分类筛选样式：0->普通；1->颜色")
    @FlagValidator({"0","1"})
    private Integer filterType;
    @Schema(title = "检索类型；0->不需要进行检索；1->关键字检索；2->范围检索")
    @FlagValidator({"0","1","2"})
    private Integer searchType;
    @Schema(title = "相同属性产品是否关联；0->不关联；1->关联")
    @FlagValidator({"0","1"})
    private Integer relatedStatus;
    @Schema(title = "是否支持手动新增；0->不支持；1->支持")
    @FlagValidator({"0","1"})
    private Integer handAddStatus;
    @Schema(title = "属性的类型；0->规格；1->参数")
    @FlagValidator({"0","1"})
    private Integer type;
}
