package com.macro.mall.sms.validate.sms;

/**
 * @Author: HanLong
 * @Date: Create in 2018/3/24 11:10
 * @Description:    模拟短信发送
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String phone, String code) {
        System.out.println("向手机号：" + phone + "，发送验证码：" + code);
    }
}
