package com.djx.learn.feign;

import com.djx.learn.feign.factory.Apple;
import com.djx.learn.feign.factory.TestFactoryBean;
import com.djx.learn.feign.feign.HttpBinFeign;
import feign.Target;
import feign.*;
import lombok.Data;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.annotation.*;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;


/**
 * @author djx
 */
@EnableFeignClients
@SpringBootApplication
public class FeignApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FeignApplication.class, args);


        TestFactoryBean bean = context.getBean(TestFactoryBean.class);
        Apple bean1 = context.getBean(Apple.class);
        Object testFactoryBean = context.getBean("testFactoryBean");

        Object bean2 = context.getBean("&testFactoryBean");

        System.out.println(bean);
        System.out.println(bean1);
        System.out.println(testFactoryBean);
        System.out.println(bean2);


        HttpBinFeign bean3 = context.getBean(HttpBinFeign.class);

        Object ip = bean3.getIp();
        System.out.println(ip);


    }


    static class TestFeignRegister implements ImportBeanDefinitionRegistrar {

        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

            // 1. 解析配置
            // 2. 注入TestFeignFactoryBean

            AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(TestFeignFactoryBean.class)
                    .addPropertyValue("type", Feign.class)
                    .addPropertyValue("name", Feign.class + "Feign")
                    .addPropertyValue("url", "http://1223")
                    .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
                    .setRole(BeanDefinition.ROLE_INFRASTRUCTURE)
                    .getBeanDefinition();
            registry.registerBeanDefinition(Feign.class + "Feign", beanDefinition);


        }


    }


    @Retention(RetentionPolicy.RUNTIME)
    @java.lang.annotation.Target(ElementType.TYPE)
    @Documented
    @Import(TestFeignRegister.class)
    @interface EnableTestFeign {


    }

    @java.lang.annotation.Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Inherited
    @interface TestFeign {

        String contextId() default "";

        String name() default "";

        String[] qualifiers() default {};

        String url() default "";

        boolean decode404() default false;

        Class<?>[] configuration() default {};

        Class<?> fallback() default void.class;

        Class<?> fallbackFactory() default void.class;

        String path() default "";

        boolean primary() default true;

    }


    @Data
    @SuppressWarnings("unchecked")
    static class TestFeignFactoryBean implements FactoryBean<Object> {

        private Class<?> type;
        private String name;

        private String url;

        @Override
        public Object getObject() throws Exception {
            return getTarget();
        }

        <T> T getTarget() {
            TestTargeter<Object> objectTestTargeter = new TestTargeter<>();

            return (T) objectTestTargeter.target(new Object(), new Target.HardCodedTarget(type, name, url));
        }

        @Override
        public Class<?> getObjectType() {
            return type;
        }
    }


    @TestFeign()
    interface Feign {
        Object doSomething();

        Object doAnything();
    }


    @Data
    static class TestTarget<T> implements Target<T> {

        private Class<T> type;
        private String name;
        private String url;


        public TestTarget(Class<T> type, String name, String url) {
            this.type = type;
            this.name = name;
            this.url = url;
        }

        @Override
        public Class<T> type() {
            return type;
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public String url() {
            return url;
        }

        @Override
        public Request apply(RequestTemplate input) {
            return null;
        }
    }

    @Data
    static class TestTargeter<T> {

        private Class<T> type;

        public T target(Object info, Target.HardCodedTarget<T> target) {
            TestReflectiveFeign testReflectiveFeign = new TestReflectiveFeign();
            return new TestReflectiveFeign().newInstance(target);
        }
    }

    @SuppressWarnings("unchecked")
    static class TestReflectiveFeign {

        public <T> T newInstance(Target.HardCodedTarget<T> target) {

            Map<String, InvocationHandlerFactory.MethodHandler> dispatch = new HashMap<>(2);

            dispatch.put("doSomething", new HttpMethodHandler());
            dispatch.put("doAnything", new HttpMethodHandler());

            T feign = (T) Proxy.newProxyInstance(target.type().getClassLoader(), new Class[]{target.type()}, (proxy, method, args) -> {

                System.out.println("test1");


                Object invoke = dispatch.get(method.getName()).invoke(args);
                System.out.println("test1");
                return invoke;
            });

            return feign;
        }

        static class HttpMethodHandler implements InvocationHandlerFactory.MethodHandler {

            private Client client;

            private MethodMetadata metadata;

            private Logger logger;


            @Override
            public Object invoke(Object[] argv) throws Throwable {

                System.out.println("发送一个请求");

                return new Object();
            }
        }

    }
}
