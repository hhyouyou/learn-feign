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

1. 回到 `Application`上的 `@EnableFeignClients` 注解中
2. 找到`@Import(FeignClientsRegistrar.class)`，

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(FeignClientsRegistrar.class)
public @interface EnableFeignClients {
    ...
}
```

3.











