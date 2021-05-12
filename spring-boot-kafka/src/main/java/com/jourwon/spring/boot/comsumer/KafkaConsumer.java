package com.jourwon.spring.boot.comsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 *
 * @author JourWon
 * @date 2021/4/30
 */
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "xiaoha", groupId = "group_id")
    public void consume(String message) {
        log.info("## consume message: {}", message);
    }

}
