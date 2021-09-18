package com.djx.learn.feign;

import com.djx.learn.feign.config.TestRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author djx
 * @date 2021/9/18 下午4:28
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(TestRegistrar.class)
public @interface EnableTestFeign {
}
