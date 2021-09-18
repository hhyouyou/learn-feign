package com.djx.learn.feign;

import com.djx.learn.feign.config.TestImport;
import com.djx.learn.feign.config.TestRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

@EnableFeignClients
@SpringBootApplication
public class FeignApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FeignApplication.class, args);
    }

}
