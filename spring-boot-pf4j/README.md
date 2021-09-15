# 工程简介
Spring Boot整合pf4j,进行插件式编程拓展

# 延伸阅读

注意事项

1.pf4j接口实现类的模块引用接口依赖时需要使用provided作用域

2.在插件实现需要引用外部对象的话，需要在插件实现类的createApplicationContext方法的上下文注入相关配置类applicationContext.register(xxx.class)

3.插件实现类继承AbstractSpringPlugin，不要调用父类的stop方法，不然下次加载的时候会报上下文已经关闭的问题

4.如果想使用spring上下文，可以在插件配置类中的createApplicationContext方法进行配置

5.日志打印问题，启动项目的时候会报如下提示，日志依赖有冲突
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/C:/dev/Maven/apache-maven-3.6.3/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/C:/dev/Maven/apache-maven-3.6.3/repository/org/slf4j/slf4j-log4j12/1.7.30/slf4j-log4j12-1.7.30.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]