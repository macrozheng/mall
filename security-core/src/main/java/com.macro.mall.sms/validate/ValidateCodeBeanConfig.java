package com.macro.mall.sms.validate;

import com.macro.mall.sms.properties.SecurityProperties;
import com.macro.mall.sms.validate.image.ImageValidateCodeGenerator;
import com.macro.mall.sms.validate.sms.DefaultSmsCodeSender;
import com.macro.mall.sms.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: HanLong
 * @Date: Create in 2018/3/24 9:54
 * @Description:
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * @ConditionalOnMissingBean 初始化的时候，先是容器中找有没有imageCodeGenerator，如果没有再用这个
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageValidateCodeGenerator imageValidateCodeGenerator = new ImageValidateCodeGenerator();
        imageValidateCodeGenerator.setSecurityProperties(securityProperties);
        return imageValidateCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeGenerator() {
        SmsCodeSender smsCodeSender = new DefaultSmsCodeSender();
        return smsCodeSender;
    }
}
