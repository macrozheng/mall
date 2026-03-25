package com.macro.mall.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OSS上传文件的回调结果
 * Created by macro on 2018/5/17.
 */
@Data
@EqualsAndHashCode
public class OssCallbackResult {
    private String filename;
    private String size;
    private String mimeType;
    private String width;
    private String height;
}
