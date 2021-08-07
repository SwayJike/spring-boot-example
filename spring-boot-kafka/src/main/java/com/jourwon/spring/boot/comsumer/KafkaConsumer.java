package com.jourwon.spring.boot.comsumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 消息消费者
 *
 * @author JourWon
 * @date 2021/4/30
 */
@Component
@Slf4j
public class KafkaConsumer {

    // /**
    //  * 自动提交
    //  * 消费者配置enable-auto-commit: true
    //  * 监听者配置type: single和ack-mode: batch
    //  *
    //  * @param message kafka消息
    //  */
    // @KafkaListener(topics = "JourWon", groupId = "group_id")
    // public void consume(String message) {
    //     // 如果消费方法报错，提交偏移量会失败
    //     // int i = 1/0;
    //     log.info("## consume message: {}", message);
    // }

    // /**
    //  * 手动提交(单条记录)
    //  * 消费者配置enable-auto-commit: false
    //  * 监听者配置type: single和ack-mode: manual
    //  *
    //  * @param record 消费者记录
    //  * @param ack 用于处理提交的引用
    //  */
    // @KafkaListener(topics = "JourWon", groupId = "group_id")
    // public void consume(ConsumerRecord<String, String> record, Acknowledgment ack) {
    //     try {
    //         log.info("接受到的数据 -> {}", JSON.toJSONString(record.value()));
    //         if (StringUtils.hasText(record.value())) {
    //             log.info("监听主题:[{}],消息体长度:[{}],partition:[{}],offset:[{}]", record.topic(),
    //                     record.value().length(), record.partition(), record.offset());
    //         }
    //     } catch (Exception e) {
    //         log.error("消费失败,原因:{}", e.getMessage(), e);
    //     } finally {
    //         log.debug("开始提交偏移量");
    //         ack.acknowledge();
    //         log.debug("提交偏移量成功");
    //     }
    // }

    /**
     * 手动提交(批量消费)
     * 消费者配置enable-auto-commit: false
     * 监听者配置type: batch和ack-mode: manual
     *
     * @param records 消费者记录
     * @param ack     用于处理提交的引用
     */
    @KafkaListener(topics = "JourWon", groupId = "group_id")
    public void consume(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
        try {
            log.info("接收消息条数:{}", records.size());
            if (!CollectionUtils.isEmpty(records)) {
                log.info("监听主题:[{}],消费消息长度:[{}],partition:[{}],offset:[{}]", records.get(0).topic(),
                        records.size(), records.get(records.size() - 1).partition(), records.get(records.size() - 1).offset());
            }
        } catch (Exception e) {
            log.error("消费失败,原因:{}", e.getMessage(), e);
        } finally {
            log.debug("开始提交偏移量");
            ack.acknowledge();
            log.debug("提交偏移量成功");
        }
    }

}
