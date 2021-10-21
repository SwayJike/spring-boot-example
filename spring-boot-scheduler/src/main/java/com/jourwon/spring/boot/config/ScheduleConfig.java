package com.jourwon.spring.boot.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jourwon.spring.boot.model.dto.ScheduleInfo;
import com.jourwon.spring.boot.util.ScheduleManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 动态定时任务配置
 *
 * @author JourWon
 * @date 2021/10/17
 */
@Slf4j
@EnableScheduling
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        log.info("ScheduleManager.scheduleList().size()=" + ScheduleManager.scheduleList().size());

        // 应用启动后3秒执行一次，然后定时fixedRate执行执行一次
        // for (ScheduleInfo scheduleInfo : ScheduleManager.scheduleList()) {
        //     try {
        //         log.info("scheduleInfo:{}", new ObjectMapper().writeValueAsString(scheduleInfo));
        //     } catch (JsonProcessingException e) {
        //         e.printStackTrace();
        //     }
        //     taskRegistrar.getScheduler().scheduleAtFixedRate(scheduleInfo.getSchedule(),
        //             new Date(System.currentTimeMillis() + 3000L),
        //             scheduleInfo.getFixedRate() * 1000L);
        // }

        for (ScheduleInfo scheduleInfo : ScheduleManager.scheduleList()) {
            try {
                log.info("scheduleInfo:{}", new ObjectMapper().writeValueAsString(scheduleInfo));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            // 添加定时任务
            taskRegistrar.addTriggerTask(scheduleInfo.getSchedule(),
                    (triggerContext) -> {
                        if (StringUtils.isEmpty(scheduleInfo.getCron())) {
                            return null;
                        }
                        // 定时任务触发,可修改定时任务的执行周期
                        CronTrigger trigger = new CronTrigger(scheduleInfo.getCron());
                        return trigger.nextExecutionTime(triggerContext);
                    });
        }
        // 设置定时任务线程池
        taskRegistrar.setScheduler(taskScheduler(ScheduleManager.scheduleList().size()));
    }

    public ThreadPoolTaskScheduler taskScheduler(int poolSize) {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();

        // 核心线程数10：线程池创建时候初始化的线程数
        taskScheduler.setPoolSize(poolSize);
        // 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        taskScheduler.setThreadNamePrefix("taskScheduler-");
        // 线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
        taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 线程池关闭的时候等待所有任务都完成
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        // 设置线程池中任务的等待时间，如果超过这个时间还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        taskScheduler.setAwaitTerminationSeconds(60);
        taskScheduler.initialize();

        return taskScheduler;
    }

}
