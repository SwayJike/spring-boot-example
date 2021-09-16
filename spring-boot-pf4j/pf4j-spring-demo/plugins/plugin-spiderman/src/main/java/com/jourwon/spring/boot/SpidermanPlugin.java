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

        // 如果想使用spring上下文，可以考虑使用如下配置
        // if (getWrapper().getPluginManager() instanceof SpringPluginManager) {
        //     SpringPluginManager springPluginManager = (SpringPluginManager) getWrapper().getPluginManager();
        //     return springPluginManager.getApplicationContext();
        // }
        //
        // throw new RuntimeException("没有找到SpringPluginManager,当前PluginManager为:" + getWrapper().getPluginManager().getClass().getCanonicalName());
    }

}
