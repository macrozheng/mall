package com.macro.mall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取OSS上传文件授权返回结果
 * Created by macro on 2018/5/17.
 */
@Data
@EqualsAndHashCode
public class OssPolicyResult {
    @Schema(title = "访问身份验证中用到用户标识")
    private String accessKeyId;
    @Schema(title = "用户表单上传的策略,经过base64编码过的字符串")
    private String policy;
    @Schema(title = "对policy签名后的字符串")
    private String signature;
    @Schema(title = "上传文件夹路径前缀")
    private String dir;
    @Schema(title = "oss对外服务的访问域名")
    private String host;
    @Schema(title = "上传成功后的回调设置")
    private String callback;
}
