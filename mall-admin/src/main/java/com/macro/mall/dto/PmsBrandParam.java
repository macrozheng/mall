package com.macro.mall.dto;

import com.macro.mall.validator.FlagValidator;
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
    private String name;
    private String firstLetter;
    @Min(value = 0)
    private Integer sort;
    @FlagValidator(value = {"0","1"}, message = "厂家状态不正确")
    private Integer factoryStatus;
    @FlagValidator(value = {"0","1"}, message = "显示状态不正确")
    private Integer showStatus;
    @NotEmpty
    private String logo;
    private String bigPic;
    private String brandStory;
}
