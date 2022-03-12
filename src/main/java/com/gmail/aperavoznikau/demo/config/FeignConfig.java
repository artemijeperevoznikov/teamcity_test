package com.gmail.aperavoznikau.demo.config;

import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;


@Configuration
@EnableFeignClients(basePackages = "com.gmail.aperavoznikau.demo.repository")
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("user", "username");
            requestTemplate.header("password", "password");
            requestTemplate.header("Accept", MediaType.APPLICATION_JSON_VALUE);
        };
    }
}


