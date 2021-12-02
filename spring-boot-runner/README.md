# 工程简介

# 延伸阅读


为什么我们需要在Spring Boot启动时运行代码？

由于多种原因，我们需要在应用程序启动时运行方法，记录重要的事情或说应用程序已启动的消息，处理数据库或文件，建立索引，创建缓存等。
启动后台进程，例如发送通知，从某些队列中获取数据等。

在Spring Boot中启动后运行方法的不同方法

每种方式都有其自身的优势。 让我们详细看一下以确定应该使用哪个

使用CommandLineRunner界面
带有ApplicationRunner界面
Spring Boot应用程序事件
@Postconstruct方法的注释
InitializingBean接口
@bean批注的Init属性

在spring boot应用中，我们可以在程序启动之前执行任何任务。
为了达到这个目的，我们需要使用CommandLineRunner或ApplicationRunner接口创建bean，
spring boot会自动监测到它们。这两个接口都有一个run()方法，在实现接口时需要覆盖该方法，
并使用@Component注解使其成为bean。CommandLineRunner和ApplicationRunner的作用是相同的。
不同之处在于CommandLineRunner接口的run()方法接收String数组作为参数，
而ApplicationRunner接口的run()方法接收ApplicationArguments对象作为参数。
当程序启动时，我们传给main()方法的参数可以被实现CommandLineRunner和ApplicationRunner接口的类的run()方法访问。
我们可以创建多个实现CommandLineRunner和ApplicationRunner接口的类。
为了使他们按一定顺序执行，可以使用@Order注解或实现Ordered接口。

CommandLineRunner和ApplicationRunner接口的run()方法在SpringApplication启动时执行。启动完成之后，应用开始运行。CommandLineRunner和ApplicationRunner的作用是在程序开始运行前执行任务或记录信息。

## 使用场景

springBoot框架项目，有时候有预加载数据需求——提前加载到缓存中或类的属性中，并且希望执行操作的时间是在容器启动末尾时间执行操作。
针对这种场景，SpringBoot提供了两个接口，分别是CommandLineRunner和ApplicationRunner。

## 区别

### 参数不一样

两个接口的实现方法一样，参数不一样，其他没什么区别。两个参数都可以接收java命令设置的参数及值。
ApplicatonRunner的实现类需要实现的方法：

```java
@Override
public void run(ApplicationArguments args) {}
```

CommandLineRunner实现类需要实现的方法

```java
@Override
public void run(String... args) {}
```

设置命令行参数：--spring.profile.active=test，但ApplicatonRunner接口的方法参数ApplicationArguments（是个对象）比CommandLineRunner接口的方法参数（是个可以接收多个string的参数）功能更强大。ApplicatonRunner接口的方法参数ApplicationArguments既可以获取参数的字符串，也可以直接获取key；CommandLineRunner接口的方法参数只能获取参数的字符串。

### 方法执行顺序不一样

ApplicationRunner接口的实现方法比CommandLineRunner接口的实现方法前执行（当然也可以设置@Order的值来决定谁先执行）

```java
2021-12-02 23:00:35.846  INFO 5812 --- [           main] c.j.s.boot.runner.MyApplicationRunner    : 启动预加载数据(ApplicationRunner)...[--spring.profile.active=test],[spring.profile.active]
2021-12-02 23:00:35.846  INFO 5812 --- [           main] c.j.s.boot.runner.MyCommandLineRunner    : 启动预加载数据(CommandLineRunner)...[--spring.profile.active=test]
2021-12-02 23:00:35.846  INFO 5812 --- [           main] c.j.s.boot.SpringBootRunnerApplication   : 应用启动...
```





使用源码分析
说到执行顺序，那么再进一步了解一下这两个方法是在什么时候执行的。这两个接口的实现执行的时机在于SpringApplication初始化之后，调用的run方法中被调用的。

```java
public ConfigurableApplicationContext run(String... args) {
        // 创建 StopWatch 对象，用于统计 run 方法启动时长。
        StopWatch stopWatch = new StopWatch();
        // 启动统计。
        stopWatch.start();
        ConfigurableApplicationContext context = null;
        Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
        // 配置 headless 属性。
        configureHeadlessProperty();
        // 获得 SpringApplicationRunListener 数组，
        // 该数组封装于 SpringApplicationRunListeners 对象的 listeners 中。
        SpringApplicationRunListeners listeners = getRunListeners(args);
        // 启动监听，遍历 SpringApplicationRunListener 数组每个元素，并执行。
        listeners.starting();
        try {
            //创建 ApplicationArguments 对象
            ApplicationArguments applicationArguments = new DefaultApplicationArguments(
                    args);
            // 加载属性配置，包括所有的配置属性（如：application.properties 中和外部的属性配置）
            ConfigurableEnvironment environment = prepareEnvironment(listeners,
                    applicationArguments);
            configureIgnoreBeanInfo(environment);
            // 打印 Banner
            Banner printedBanner = printBanner(environment);
            // 创建容器
            context = createApplicationContext();

            // 异常报告器
            exceptionReporters = getSpringFactoriesInstances(
                    SpringBootExceptionReporter.class,
                    new Class[]{ConfigurableApplicationContext.class}, context);
            // 准备容器，组件对象之间进行关联
            prepareContext(context, environment, listeners, applicationArguments,
                    printedBanner);
            // 初始化容器
            refreshContext(context);
            // 初始化操作之后执行，默认实现为空。
            afterRefresh(context, applicationArguments);
            // 停止时长统计
            stopWatch.stop();
            // 打印启动日志
            if (this.logStartupInfo) {
                new StartupInfoLogger(this.mainApplicationClass)
                        .logStarted(getApplicationLog(), stopWatch);
            }
            // 通知监听器：容器启动完成。
            listeners.started(context);
            // 调用 ApplicationRunner 和 CommandLineRunner 的运行方法。
            callRunners(context, applicationArguments);
        } catch (Throwable ex) {
            // 异常处理
            handleRunFailure(context, ex, exceptionReporters, listeners);
            throw new IllegalStateException(ex);
        }

        try {
            // 通知监听器：容器正在运行。
            listeners.running(context);
        } catch (Throwable ex) {
            // 异常处理
            handleRunFailure(context, ex, exceptionReporters, null);
            throw new IllegalStateException(ex);
        }
        return context;
    }
```

我们可以看到，在try方法的最后，会执行一个callRunners的方法，在此方法中会对实现这两个接口的实现类进行调用。


```java
private void callRunners(ApplicationContext context, ApplicationArguments args) {
	List<Object> runners = new ArrayList<>();
	runners.addAll(context.getBeansOfType(ApplicationRunner.class).values());
	runners.addAll(context.getBeansOfType(CommandLineRunner.class).values());
	AnnotationAwareOrderComparator.sort(runners);
	for (Object runner : new LinkedHashSet<>(runners)) {
		if (runner instanceof ApplicationRunner) {
			callRunner((ApplicationRunner) runner, args);
		}
		if (runner instanceof CommandLineRunner) {
			callRunner((CommandLineRunner) runner, args);
		}
	}
}

private void callRunner(ApplicationRunner runner, ApplicationArguments args) {
	try {
		(runner).run(args);
	} catch (Exception ex) {
		throw new IllegalStateException("Failed to execute ApplicationRunner", ex);
	}
}

private void callRunner(CommandLineRunner runner, ApplicationArguments args) {
	try {
		(runner).run(args.getSourceArgs());
	} catch (Exception ex) {
		throw new IllegalStateException("Failed to execute CommandLineRunner", ex);
	}
}
```

通过以上代码，我们也就了解到这两个接口的实现类的执行时机了。