[TOC]



## feign

### What?

**Feign** 简化了远程调用的方式, 让使用者把精力放在接口的业务上,不被复杂的配置等干扰.

很好,这很符合spring的一贯风格.  懒, 果然是促进人类进步[狗头]

官方的文档概括的非常精辟:

声明式的REST Client: `fegin`为使用`JAX-RS` 或者 `Spring MVC `注解修饰的接口创建一个动态的实现.

> Declarative REST Client: Feign creates a dynamic implementation of an interface decorated with JAX-RS or Spring MVC annotations
>
> [spring-cloud-openfeign](https://spring.io/projects/spring-cloud-openfeign)

### How? 快速使用

#### 引入依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
    <version>使用最新版本(如果使用是在spring-boot下单独引入,注意版本匹配)</version>
</dependency>
```



#### 创建远程服务对应接口

先在启动类加上 `@EnableFeignClients`  注解.

然后创建远程服务接口, 配置服务对应的name, url ,path, 方法的入参,出参

```java
@FeignClient(name = "user-client", url = "${user.url}", path = "/user")
public interface UserFeign {
    
    @GetMapping("/{userId}")
    ServerResponse<UserInfoVO> getUserInfo(@PathVariable String userId);
}
```

> 如果是在同一注册中心的服务, 在 `name` 中直接填写对应服务名,即可访问到服务. 无需填写`url`

#### 业务中使用

```java
public class orderService {

    @Resource
    private UserFeign userFeign;

    @Test
    void getOrderInfo() {
        ...
        ServerResponse<UserInfoVO> userInfo = userFeign.getUserInfo();
        ...
    }
}
```



### 额外配置

#### 拦截器

example 认证

#### 回调 fall back







### Why? 源码导读

读源码时，我划分三步，项目启动时，注入对象时，实际调用接口时

1. 一是在项目启动时，发现有注解的feign接口，解析注解和配置信息,生成对应的`BeanDefinition`, 注册到`beanDefinitionMap`中。
2. 接下来是在注入feign服务时，根据步骤一保证了bean能被找到，并根据`BeanDefinition`实例化一个代理。
3. 最后是实际调用远程接口时，走的代理类，` methodHandler.invoke()`，默认使用`HttpUrlConnection` 去访问接口。



#### 1. 解析注解信息

1. 回到 `Application`上，点开 `@EnableFeignClients` 注解，我们找到了`@Import(FeignClientsRegistrar.class)`

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(FeignClientsRegistrar.class)
public @interface EnableFeignClients {
    String[] value() default {};
	String[] basePackages() default {};
	Class<?>[] basePackageClasses() default {};
	Class<?>[] defaultConfiguration() default {};
	Class<?>[] clients() default {};
}
```

2. 进入`FeignClientsRegistrar.class`, 可以看到实现了`ImportBeanDefinitionRegistrar` 接口。

   > 在spring解析配置类时，处理`@Import` 注解标注对象的时候，如果实现了`ImportBeanDefinitionRegistrar` 接口，会去执行其中的 registerBeanDefinitions() 方法。
   >
   > 具体可以从`org.springframework.context.annotation.ConfigurationClassParser`开始看

```java
class FeignClientsRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {

    @Override
	public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        // 注册默认配置（EnableFeignClients中有个defaultConfiguration[]）
		registerDefaultConfiguration(metadata, registry);
        // 解析@FeignClient中的信息，并通过FeignClientFactoryBean注册beanDefinition
		registerFeignClients(metadata, registry);
	}
}
```

3. 可以看到，把`@FeignClient`中的`annotationMetadata`信息，都放入了 `FeignClientFactoryBean` ，最后通过`factoryBean.getObject();` 转换成 `beanDefinition`。

```java
private void registerFeignClient(BeanDefinitionRegistry registry, AnnotationMetadata annotationMetadata,
			Map<String, Object> attributes) {
    String className = annotationMetadata.getClassName();
    Class clazz = ClassUtils.resolveClassName(className, null);
    ConfigurableBeanFactory beanFactory = registry instanceof ConfigurableBeanFactory
        ? (ConfigurableBeanFactory) registry : null;
    String contextId = getContextId(beanFactory, attributes);
    String name = getName(attributes);
    FeignClientFactoryBean factoryBean = new FeignClientFactoryBean();
    factoryBean.setBeanFactory(beanFactory);
    factoryBean.setName(name);
    factoryBean.setContextId(contextId);
    factoryBean.setType(clazz);
    factoryBean.setRefreshableClient(isClientRefreshEnabled());
    definition = BeanDefinitionBuilder.genericBeanDefinition(clazz, () -> {
        factoryBean.setUrl(getUrl(beanFactory, attributes));
        factoryBean.setPath(getPath(beanFactory, attributes));
        	... 略
            ... 配置fallback
            ... 配置fallbackFactory
            return factoryBean.getObject();
    });
    definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
    definition.setLazyInit(true);
    ...略
    registerOptionsBeanDefinition(registry, contextId);
}
```

4. 然后我们再进入 `org.springframework.cloud.openfeign.FeignClientFactoryBean#getObject` 中。

```java

@Override
public Object getObject() {
    return getTarget();
}

/**
* @param <T> the target type of the Feign client
* @return a {@link Feign} client created with the specified data and the context
* information
*/
<T> T getTarget() {
    // 获取feign的上下文 FeignContext
    FeignContext context = beanFactory != null ? beanFactory.getBean(FeignContext.class)
        : applicationContext.getBean(FeignContext.class);
    // 构建feign的配置
    Feign.Builder builder = feign(context);

    if (!StringUtils.hasText(url)) {
        if (url != null && LOG.isWarnEnabled()) {
            LOG.warn("The provided URL is empty. Will try picking an instance via load-balancing.");
        }
        else if (LOG.isDebugEnabled()) {
            LOG.debug("URL not provided. Will use LoadBalancer.");
        }
        if (!name.startsWith("http")) {
            url = "http://" + name;
        }
        else {
            url = name;
        }
        url += cleanPath();
        return (T) loadBalance(builder, context, new HardCodedTarget<>(type, name, url));
    }
    if (StringUtils.hasText(url) && !url.startsWith("http")) {
        url = "http://" + url;
    }
    String url = this.url + cleanPath();
    Client client = getOptional(context, Client.class);
    if (client != null) {
        if (client instanceof FeignBlockingLoadBalancerClient) {
            // not load balancing because we have a url,
            // but Spring Cloud LoadBalancer is on the classpath, so unwrap
            client = ((FeignBlockingLoadBalancerClient) client).getDelegate();
        }
        if (client instanceof RetryableFeignBlockingLoadBalancerClient) {
            // not load balancing because we have a url,
            // but Spring Cloud LoadBalancer is on the classpath, so unwrap
            client = ((RetryableFeignBlockingLoadBalancerClient) client).getDelegate();
        }
        builder.client(client);
    }
    Targeter targeter = get(context, Targeter.class);
    return (T) targeter.target(this, builder, context, new HardCodedTarget<>(type, name, url));
}
```











