package com.djx.learn.feign.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author djx
 * @date 2021/12/29 上午11:36
 */
@Component
public class TestFactoryBean implements FactoryBean<Apple> {
    @Override
    public Apple getObject() throws Exception {
        Apple apple = new Apple();
        apple.setColor("red");
        apple.setOrigin("shandong");
        return apple;
    }

    @Override
    public Class<?> getObjectType() {
        return Apple.class;
    }
}
