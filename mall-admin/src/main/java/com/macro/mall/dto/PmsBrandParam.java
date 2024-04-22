package com.macro.mall.dto;

import com.macro.mall.validator.FlagValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

/**
 * 品牌请求参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode
public class PmsBrandParam {
    @NotEmpty
    @Schema(title =  "品牌名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(title =  "品牌首字母")
    private String firstLetter;
    @Min(value = 0)
    @Schema(title =  "排序字段")
    private Integer sort;
    @FlagValidator(value = {"0","1"}, message = "厂家状态不正确")
    @Schema(title =  "是否为厂家制造商")
    private Integer factoryStatus;
    @FlagValidator(value = {"0","1"}, message = "显示状态不正确")
    @Schema(title =  "是否进行显示")
    private Integer showStatus;
    @NotEmpty
    @Schema(title =  "品牌logo",requiredMode = Schema.RequiredMode.REQUIRED)
    private String logo;
    @Schema(title =  "品牌大图")
    private String bigPic;
    @Schema(title =  "品牌故事")
    private String brandStory;
}
