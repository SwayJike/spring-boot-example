package com.jourwon.spring.boot.provider.service;

import com.jourwon.spring.boot.consumer.DemoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

/**
 * Default {@link DemoService}
 *
 * @author JourWon
 * @date 2021/3/1
 */
@DubboService(version = "${demo.service.version}")
public class DefaultDemoService implements DemoService {

    /**
     * The default value of ${dubbo.application.name} is ${spring.application.name}
     */
    @Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public String sayHello(String name) {
        return String.format("[%s] : Hello, %s", serviceName, name);
    }

}
