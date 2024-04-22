package com.macro.mall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OSS上传成功后的回调参数
 * Created by macro on 2018/5/17.
 */
@Data
@EqualsAndHashCode
public class OssCallbackParam {
    @Schema(title = "请求的回调地址")
    private String callbackUrl;
    @Schema(title = "回调是传入request中的参数")
    private String callbackBody;
    @Schema(title = "回调时传入参数的格式，比如表单提交形式")
    private String callbackBodyType;
}
