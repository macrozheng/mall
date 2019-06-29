package com.macro.mall.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MallSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallSearchApplication.class, args);
    }
}
