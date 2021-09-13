package com.jourwon.spring.boot;

import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPlugin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpidermanPlugin extends SpringPlugin {

    public SpidermanPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        System.out.println("spriderPlugin.start()");
    }

    @Override
    public void stop() {
        System.out.println("spriderPlugin.stop()");
        super.stop();
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
