# 企业级spring-boot案例-Spring事件发布与监听

[toc]



企业级spring-boot案例系列文章上线了，涵盖了大部分企业级的spring-boot使用场景，会不定期进行更新，企业级spring-boot案例源码地址：[https://gitee.com/JourWon/spring-boot-example](https://gitee.com/JourWon/spring-boot-example)，欢迎各位大佬一起学习和指正

```java
企业级spring-boot案例
|----Spring Boot整合actuator,实现服务监控管理 spring-boot-actuator
|----Spring Boot集成事件发布及监听 spring-boot-applicationevent
|----Spring Boot整合异步线程池 spring-boot-async
|----Spring Boot整合自定义banner spring-boot-banner
|----Spring Boot整合本地缓存caffeine spring-boot-cache-caffeine
|----Spring Boot整合验证码captcha spring-boot-captcha
|----Spring Boot整合cors跨域资源共享 spring-boot-cors
|----Spring Boot整合jpa实现增删改查 spring-boot-data-jpa
|----Spring Boot整合PostgreSQL spring-boot-data-postgresql
|----Spring Boot整合Redis spring-boot-data-redis
|----Spring Boot整合Docker spring-boot-docker
|----Spring Boot设计模式之工厂模式 spring-boot-dp-factory
|----Spring Boot整合Druid数据库连接池 spring-boot-druid
|----Spring Boot整合dubbo spring-boot-dubbo
|----Spring Boot整合EasyExcel,实现Excel导入导出 spring-boot-easyexcel
|----Spring Boot整合邮件发送 spring-boot-email
|----Spring Boot整合全局异常处理,接口统一响应对象 spring-boot-exception-handler
|----Spring Boot整合flyway,数据库版本控制 spring-boot-flyway
|----Spring Boot整合git插件,将项目打包为tar.gz,并带上git版本号,然后通过sh脚本快速部署 spring-boot-git-commit-id-plugin
|----Spring Boot快速入门-HelloWorld spring-boot-helloworld
|----Spring Boot整合HikariCP数据库连接池 spring-boot-hikaricp
|----Spring Boot整合https spring-boot-https
|----Spring Boot整合Jackson,实现数据脱敏 spring-boot-json-desensitization
|----Spring Boot整合Kafka spring-boot-kafka
|----Spring Boot整合Knife4j-API接口文档 spring-boot-knife4j
|----Spring Boot整合log4j2日志 spring-boot-log4j2
|----Spring Boot整合logback日志 spring-boot-logback
|----Spring Boot整合MyBatis spring-boot-mybatis
|----Spring Boot整合MyBatis,使用注解方式 spring-boot-mybatis-annotation
|----Spring Boot整合MyBatis逆向工程 spring-boot-mybatis-generator
|----Spring Boot整合通用mapper spring-boot-mybatis-mapper
|----Spring Boot整合MyBatis多数据源 spring-boot-mybatis-multi-datasource
|----Spring Boot整合MyBatis,使用pagehelper进行分页 spring-boot-mybatis-pagehelper
|----Spring Boot整合MyBatis脱敏插件,实现手机号等信息脱敏 spring-boot-mybatis-plugin-sensitive
|----Spring Boot整合Mybatis-Plus spring-boot-mybatis-plus
|----Spring Boot整合MyBatis Plus代码生成器 spring-boot-mybatis-plus-generator
|----Spring Boot整合MyBatis Plus多数据源 spring-boot-mybatis-plus-multi-datasource
|----Spring Boot整合pf4j,进行插件式编程拓展 spring-boot-pf4j
|----Spring Boot加载配置文件 spring-boot-properties
|----Spring Boot整合接口限流-guava单体方式 spring-boot-ratelimit-guava
|----Spring Boot整合接口限流-redis集群方式 spring-boot-ratelimit-redis
|----Spring Boot整合RestTemplate,实现服务间调用 spring-boot-resttemplate
|----Spring Boot整合retrofit,支持通过接口的方式发起HTTP请求 spring-boot-retrofit
|----Spring Boot启动时的运行方法 spring-boot-runner
|----Spring Boot整合定时任务scheduler spring-boot-scheduler
|----Spring Boot整合Screw,一键生成数据库文档 spring-boot-screw
|----Spring Boot整合Shiro spring-boot-shiro
|----Spring Boot整合Swagger3-API接口文档 spring-boot-swagger3
|----Spring Boot整合模板引擎Thymeleaf spring-boot-thymeleaf
|----Spring Boot整合undertow spring-boot-undertow
|----Spring Boot项目打包成war包 spring-boot-war
|----Spring Boot整合zip,压缩和解压文件 spring-boot-zip
```

## 事件机制重要概念

在一个事件体系中，有以下几个重要的概念。

1、事件源：事件对象的产生者，任何一个EventObject都有一个来源

2、事件监听器注册表：当事件框架或组件收到一个事件后，需要通知所有相关的事件监听器来进行处理，这个时候就需要有个存储监听器的地方，也就是事件监听器注册表。事件源与事件监听器关联关系的存储。

3、事件广播器：事件广播器在整个事件机制中扮演一个中介的角色，当事件发布者发布一个事件后，就需要通过广播器来通知所有相关的监听器对该事件进行处理。

下图就是事件机制的结构图

![img](https://img-blog.csdnimg.cn/img_convert/b61b04c104664662289f2c0ae8ba0055.png)



## 简介

在使用 `Spring` 构建的应用程序中，适当使用事件发布与监听的机制可以使我们的代码灵活度更高，降低耦合度。`Spring` 提供了完整的事件发布与监听模型，在该模型中，事件发布方只需将事件发布出去，无需关心有多少个对应的事件监听器；监听器无需关心是谁发布了事件，并且可以同时监听来自多个事件发布方发布的事件，通过这种机制，事件发布与监听是解耦的。



## Spring 事件

对于SpringApplicationContext(BeanFactory)而言，在整个应用运行过程中(包括应用的启动、销毁)，会发布各种应用事件。开发者也可以实现自己的事件，从而起到扩展spring框架的作用 。

Spring的事件(ApplicationEvent)为 Bean与 Bean之间的消息通信提供了支持。当一个Bean处理完一个任务之后，希望另外一个 Bean知道并能做相应的处理，这时我们就需要让另外一个 Bean监听当前 Bean所发送的事件。

sprjng借助于 org.springframewofk.context.event.ApplicationEvent抽象类及其子类实现事件的发布，与此同时，借助于 org.springframework.context.ApplicationListener接口及其实现者实现事件的监听，这两者构成了观察者 ( observer) 模式。

监听者模式

> 监听者模式包含了一个监听者Listener与之对应的事件Event，还有一个事件发布者EventPublish，过程就是EventPublish发布一个事件，被监听者捕获到，然后执行事件相应的方法

观察者模式

> 观察者模式是一对多的模式，一个被观察者Observable和多个观察者Observer，被观察者中存储了所有的观察者对象，当被观察者接收到一个外界的消息，就会遍历广播推送消息给所有的观察者



### Spring 事件核心接口或类

| 序号 | 接口或类                                    | 描述                                                         |
| ---- | ------------------------------------------- | ------------------------------------------------------------ |
| 1    | **事件**：ApplicationEvent                  | 该抽象类继承了EventObject，EventObject是JDK中的类，并建议所有的事件都应该继承自EventObject |
| 2    | **事件监听器**：ApplicationListener         | 是一个接口，该接口继承了EventListener接口。EventListener接口是JDK中的，建议所有的事件监听器都应该继承EventListener。监听器是用于接收事件，并触发事件的操作，这样说起来可能有点费解，简单的说就是，Listener是监听ApplicationContext.publishEvent，方法的调用，一旦调用publishEvent，就会执行ApplicaitonListener中的onApplicationEvent方法 |
| 3    | **事件发布器**：ApplicationEventPublisher   | ApplicationContext实现了该接口，在ApplicationContext的抽象实现类AbstractApplicationContext中做了实现，可以通过publishEvent方法发布事件 |
| 4    | **事件源**：ApplicationContext              | `ApplicationContext` 是 Spring 中的核心容器，在事件监听中 ApplicationContext 可以作为事件的发布者，也就是事件源。因为 ApplicationContext 继承自 ApplicationEventPublisher。在 `ApplicationEventPublisher` 中定义了事件发布的方法：`publishEvent(Object event)`<br />在Spring中通常是ApplicationContext本身担任监听器注册表的角色，在其子类AbstractApplicationContext中就聚合了事件广播器ApplicationEventMulticaster和事件监听器ApplicationListnener，并且提供注册监听器的addApplicationListnener方法。 |
| 5    | **事件广播器**：ApplicationEventMulticaster | 用于事件监听器的注册和事件的广播。监听器的注册就是通过它来实现的，它的作用是把 Applicationcontext 发布的 Event 广播给它的监听器列表。 |



###  Spring 事件机制设计类图

下面我们来看看Spring的事件机制设计类图

![img](https://img-blog.csdnimg.cn/img_convert/b13f4ffd314f2ef3aa8c50c4e4c99b6b.png)



### Spring 事件执行流程

1、当一个事件源产生事件时，它通过事件发布器ApplicationEventPublisher的pulishEvent方法发布事件

2、事件广播器ApplicationEventMulticaster通过父类AbstractApplicationEventMulticaster的getApplicationListeners方法去事件注册表ApplicationContext中找到事件监听器ApplicationListener，并且通过invokeListener方法执行监听器的具体逻辑

3、逐个执行监听器ApplicationListener的onApplicationEvent方法，从而完成事件监听器的逻辑。

在Spring中，使用注册监听接口，除了继承ApplicationListener接口外，还可以使用注解@EventListener来监听一个事件，同时该注解还支持SpEL表达式，来触发监听的条件，比如只接受编码为001的事件，从而实现一些个性化操作。



### Spring 相关事件

#### Spring 内置事件

`Spring` 提供了内置事件。`Spring` 的核心是 `ApplicationContext`， 当加载 `Bean` 的时候，`ApplicationContext` 会发布某些类型的事件，然后通过 `ApplicationEvent` 和 `ApplicationListener`进行处理。

| 序号 | 事件                  | 描述                                                   |
| ---- | --------------------- | ------------------------------------------------------ |
| 1    | ContextStartedEvent   | 容器启动的时候触发                                     |
| 2    | ContextRefreshedEvent | 容器初始化或刷新 `ApplicationContext` 时，将发布此事件 |
| 3    | ContextStoppedEvent   | 容器停止的时候触发                                     |
| 4    | ContextClosedEvent    | 容器关闭的时候触发                                     |



#### Spring Boot 内置事件

在Spring Boot的1.5.x中，提供了几种事件，供我们在开发过程中进行更加便捷的扩展及差异化操作。

| 序号 | 事件                                | 描述                                                         |
| ---- | ----------------------------------- | ------------------------------------------------------------ |
| 1    | ApplicationStartingEvent            | springboot启动开始的时候执行的事件                           |
| 2    | ApplicationEnvironmentPreparedEvent | spring boot对应Enviroment已经准备完毕，但此时上下文context还没有创建。在该监听中获取到ConfigurableEnvironment后可以对配置信息做操作，例如：修改默认的配置信息，增加额外的配置信息等等 |
| 3    | ApplicationPreparedEvent            | spring boot上下文context创建完成，但此时spring中的bean是没有完全加载完成的。在获取完上下文后，可以将上下文传递出去做一些额外的操作。值得注意的是：在该监听器中是无法获取自定义bean并进行操作的 |
| 4    | ApplicationReadyEvent               | springboot加载完成时候执行的事件                             |
| 5    | ApplicationFailedEvent              | spring boot启动异常时执行事件                                |

![](https://img-blog.csdnimg.cn/2020012716281991.jpeg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3h1ZW1lbmdydWkxMg==,size_16,color_FFFFFF,t_70)


从官网文档中，我们可以知道，由于一些事件是在上下文为加载完触发的，所以无法使用注册bean的方式来声明，文档中可以看出，可以通过SpringApplication.addListeners(…)或者SpringApplicationBuilder.listeners(…)来添加，或者添加META-INF/spring.factories文件中添加监听类也是可以的，这样会自动加载。



## 自定义事件

创建/监听事件应该以下准则

- 事件类应该继承 `ApplicationEvent`
- 事件的发布者应该注入`ApplicationEventPublisher`
- 事件监听者应该实现`ApplicationListener`

1. 创建事件类 继承 `ApplicationEvent`

   ```java
   public class CustomApplicationEvent extends ApplicationEvent {
   
       private String message;
   
       public CustomApplicationEvent(Object source, String message) {
           super(source);
           this.message = message;
       }
   
       public String getMessage() {
           return message;
       }
   
   }
   ```

2. 创建事件的发布者 注入`ApplicationEventPublisher`

   ```java
   @Slf4j
   @Component
   public class CustomApplicationEventPublisher {
   
       @Resource
       ApplicationEventPublisher applicationEventPublisher;
   
       public void publishEvent(String message) {
           log.info("开始发布自定义事件");
           CustomApplicationEvent customApplicationEvent = new CustomApplicationEvent(this, message);
           // 发布事件
           applicationEventPublisher.publishEvent(customApplicationEvent);
           log.info("发布自定义事件结束");
       }
   
   }
   ```

3. 创建事件的监听者 实现ApplicationListener接口

   ```java
   @Slf4j
   @Component
   public class CustomApplicationListener implements ApplicationListener<CustomApplicationEvent> {
   
       @Override
       public void onApplicationEvent(CustomApplicationEvent event) {
           log.info("onApplicationEvent方法接收到的消息:{}", event.getMessage());
       }
   
   }
   ```



### 注解驱动

`Spring 4.1`后提供了 `@EventLister` ，不需要手动实现 `ApplicationListener`接口实现事件的监听，同时也可以配置`@Async` 使用

```java
public @interface EventListener {

    @AliasFor("classes")
    Class<?>[] value() default {};

    @AliasFor("value")
    Class<?>[] classes() default {};

    String condition() default "";

}
```

- value: classes别名
- classes： 可以指定监听的消息对象类型
- condition：指定条件下触发事件监听, 当表达式计算结果为true时才触发



**事件监听**

```java
@Slf4j
@Component
public class AnnotationCustomApplicationListener {

    @EventListener(CustomApplicationEvent.class)
    public void listener(CustomApplicationEvent customApplicationEvent) {
        log.info("EventListener注解方式接收到的消息为:{}", customApplicationEvent.getMessage());
    }

}
```



**测试**

```java
@SpringBootTest
public class SpringBootApplicationeventApplicationTests {

    @Resource
    private CustomApplicationEventPublisher eventPublisher;

    @Test
    public void publishTest() {
        eventPublisher.publishEvent("发布消息");
    }

}
```

输出结果

```java
2021-12-08 01:08:12.036  INFO 10624 --- [           main] .j.s.b.p.CustomApplicationEventPublisher : 开始发布自定义事件
2021-12-08 01:08:12.036  INFO 10624 --- [           main] c.j.s.b.l.CustomApplicationListener      : onApplicationEvent方法接收到的消息:发布消息
2021-12-08 01:08:12.036  INFO 10624 --- [           main] .b.l.AnnotationCustomApplicationListener : EventListener注解方式接收到的消息为:发布消息
2021-12-08 01:08:12.036  INFO 10624 --- [           main] .j.s.b.p.CustomApplicationEventPublisher : 发布自定义事件结束
```



### 异步事件

`Spring` 中的事件默认情况下是同步的，发布者线程会进入阻塞状态，直到所有的监听器处理完事件。如果想让事件监听异步执行，需要在监听器上添加`@Async`, 同时主启动类上添加`@EnableAsync`注解

```java
@Slf4j
@Component
public class AsynCustomApplicationListener {

    @Async
    @EventListener(CustomApplicationEvent.class)
    public void asyncListener(CustomApplicationEvent customApplicationEvent) {
        log.info("异步事件监听,当前线程:{},消息为:{}", Thread.currentThread().getName(), customApplicationEvent.getMessage());
    }

}
```

同时支持线程池配置

```java
@EnableAsync
@Configuration
public class AsyncTaskExecutorConfig {

    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数10：线程池创建时候初始化的线程数
        executor.setCorePoolSize(10);
        // 最大线程数20：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(20);
        // 缓冲队列200：用来缓冲执行任务的队列
        executor.setQueueCapacity(200);
        // 允许线程的空闲时间60秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(60);
        // 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        executor.setThreadNamePrefix("taskExecutor-");
        // 线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 线程池关闭的时候等待所有任务都完成
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 设置线程池中任务的等待时间，如果超过这个时间还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        executor.setAwaitTerminationSeconds(60);

        // 如果不初始化，会出现找不到执行器
        executor.initialize();
        return executor;
    }

}
```

输出结果

```java
2021-12-08 01:09:02.719  INFO 11556 --- [           main] .j.s.b.p.CustomApplicationEventPublisher : 开始发布自定义事件
2021-12-08 01:09:02.719  INFO 11556 --- [           main] c.j.s.b.l.CustomApplicationListener      : onApplicationEvent方法接收到的消息:发布消息
2021-12-08 01:09:02.735  INFO 11556 --- [           main] .b.l.AnnotationCustomApplicationListener : EventListener注解方式接收到的消息为:发布消息
2021-12-08 01:09:02.751  INFO 11556 --- [           main] .j.s.b.p.CustomApplicationEventPublisher : 发布自定义事件结束
2021-12-08 01:09:02.751  INFO 11556 --- [ taskExecutor-1] c.j.s.b.l.AsynCustomApplicationListener  : 异步事件监听,当前线程:taskExecutor-1,消息为:发布消息
```
