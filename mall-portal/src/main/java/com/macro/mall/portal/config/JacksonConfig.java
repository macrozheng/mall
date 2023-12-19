package com.macro.mall.portal.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;


/**
 * Jackson相关配置
 * 配置json不返回null的字段
 * Created by macro on 2018/8/2.
 */
@Configuration
public class JacksonConfig {
    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        // 通过该方法对mapper对象进行设置，所有序列化的对象都将按该规则进行系列化
        // Include.ALWAYS 默认
        // Include.NON_DEFAULT 属性为默认值不序列化
        // Include.NON_EMPTY 属性为空（""）或者为NULL都不序列化，返回的json是没有这个字段的
        // Include.NON_NULL 属性为NULL的字段不序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 字段保留，将null值转为""
//        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>()
//        {
//            @Override
//            public void serialize(Object o, JsonGenerator jsonGenerator,
//                                  SerializerProvider serializerProvider)
//                    throws IOException, JsonProcessingException
//            {
//                jsonGenerator.writeString("");
//            }
//        });
        return objectMapper;
    }
}
