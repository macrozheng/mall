package com.macro.mall.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OSS上传成功后的回调参数
 * Created by macro on 2018/5/17.
 */
@Data
@EqualsAndHashCode
public class OssCallbackParam {
    private String callbackUrl;
    private String callbackBody;
    private String callbackBodyType;
}
