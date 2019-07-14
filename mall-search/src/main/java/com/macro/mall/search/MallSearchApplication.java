package com.macro.mall.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.macro.mall.search.component.EsProductMsgListener;

@SpringBootApplication
//@EnableDiscoveryClient
public class MallSearchApplication {

    public static void main(String[] args) {
    	ConfigurableApplicationContext context= SpringApplication.run(MallSearchApplication.class, args);
    }

}
