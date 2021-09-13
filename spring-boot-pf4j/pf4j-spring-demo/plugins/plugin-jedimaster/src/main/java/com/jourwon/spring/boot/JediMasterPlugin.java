package com.jourwon.spring.boot;

import org.pf4j.Extension;
import org.pf4j.PluginWrapper;
import org.pf4j.demo.api.Hero;
import org.pf4j.spring.SpringPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JediMasterPlugin extends SpringPlugin {

    public JediMasterPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        System.out.println("jediMaster.start()");
    }

    @Override
    public void stop() {
        System.out.println("jediMaster.stop()");
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

    @Extension
    public static class JediMaster implements Hero {

        @Autowired
        private JediMasterBean jediMasterBean;

        @Override
        public void eat() {
            jediMasterBean.eat();
        }

        @Override
        public void say() {
            jediMasterBean.say();
        }

        @Override
        public void walk() {
            jediMasterBean.walk();
        }

        @Override
        public void run() {
            jediMasterBean.run();
        }

        @Override
        public void attack() {
            jediMasterBean.attack();
        }

        @Override
        public void defense() {
            jediMasterBean.defense();
        }
    }

    @Extension
    public static class JediKnight implements Hero {

        @Autowired
        private JediKnightBean jediKnightBean;

        @Override
        public void eat() {
            jediKnightBean.eat();
        }

        @Override
        public void say() {
            jediKnightBean.say();
        }

        @Override
        public void walk() {
            jediKnightBean.walk();
        }

        @Override
        public void run() {
            jediKnightBean.run();
        }

        @Override
        public void attack() {
            jediKnightBean.attack();
        }

        @Override
        public void defense() {
            jediKnightBean.defense();
        }
    }

}
