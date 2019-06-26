package com.macro.mall.sms.properties;

/**
 * @Author: HanLong
 * @Date: Create in 2018/3/21 22:08
 * @Description:    验证码相关的配置
 */
public class ValidateCodeProperties {

    private ImageProperties image = new ImageProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();

    public ImageProperties getImage() {
        return image;
    }

    public void setImage(ImageProperties image) {
        this.image = image;
    }

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }
}
