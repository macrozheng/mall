package com.ricadro.orderserviceapi.enums;

import lombok.Getter;

/**
 * @Author: ricadro
 * @Date: 2020/1/17 09:40
 */
@Getter
public enum ResCodeEnum {

    /**
     * 响应交易码
     */
    SUCCESS("00000", "响应成功"),
    FAIL("00001","响应失败");

    private String code;

    private String msg;

    ResCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
