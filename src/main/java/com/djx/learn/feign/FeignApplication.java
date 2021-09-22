package com.djx.learn.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;


/**
 * @author djx
 */
@EnableFeignClients
@SpringBootApplication
public class FeignApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FeignApplication.class, args);
    }

}
