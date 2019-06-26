package com.macro.mall.sms.validate;

import java.time.LocalDateTime;

/**
 * @Author: HanLong
 * @Date: Create in 2018/3/24 11:06
 * @Description:
 */
public class ValidateCode {

    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 是否过期
     * @return
     */
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(getExpireTime());
    }
}
