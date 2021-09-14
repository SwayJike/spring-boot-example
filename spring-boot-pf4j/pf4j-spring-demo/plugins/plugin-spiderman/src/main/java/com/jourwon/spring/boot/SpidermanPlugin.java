package com.jourwon.spring.boot;

import lombok.extern.slf4j.Slf4j;
import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPlugin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 蜘蛛侠 插件
 * 继承 SpringPlugin 可以使用 ApplicationContext
 * 
 * @author JourWon
 * @date 2021/9/14
 */
@Slf4j
public class SpidermanPlugin extends SpringPlugin {

    public SpidermanPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        log.info("spidermanPlugin.start()");
    }

    @Override
    public void stop() {
        log.info("spidermanPlugin.stop()");
        // to close applicationContext
        // super.stop();
    }

    @Override
    protected ApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        ClassLoader pluginClassLoader = getWrapper().getPluginClassLoader();
        applicationContext.setClassLoader(pluginClassLoader);
        applicationContext.register(SpringConfiguration.class);
        applicationContext.refresh();
        return applicationContext;
    }

}
