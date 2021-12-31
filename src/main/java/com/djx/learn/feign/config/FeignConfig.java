package com.djx.learn.feign.config;

import feign.Feign;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author djx
 * @date 2021/8/9 下午3:23
 */
@Configuration
public class FeignConfig {

    @Bean
    public BasicAuthRequestInterceptor getBasicAuthRequestInterceptorBean() {
        return new BasicAuthRequestInterceptor("admin", "public");
    }

    /**
     * 注入自定义 queryMap编码
     * 注入自定义日志等级
     *
     * @return Feign.Builder
     */
    @Bean
    public Feign.Builder feignHystrixBuilder() {
        return Feign.builder()
                .queryMapEncoder(new FeignAutoQueryMapEncoder())
                .logLevel(Logger.Level.FULL);
    }

}
