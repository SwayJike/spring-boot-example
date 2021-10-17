package com.jourwon.spring.boot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 定时任务信息
 *
 * @author JourWon
 * @date 2021/10/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleInfo {

    /**
     * 任务类名
     */
    private String taskClazz;

    /**
     * cron表达式
     */
    private String cron;

    /**
     * 定时任务
     */
    private Runnable schedule;

}
