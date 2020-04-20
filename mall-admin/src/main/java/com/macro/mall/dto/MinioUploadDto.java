package com.macro.mall.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件上传返回结果
 * Created by macro on 2019/12/25.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MinioUploadDto {
    private String url;
    private String name;
}
