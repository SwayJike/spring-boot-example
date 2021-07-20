package com.jourwon.spring.boot.provider;

import com.alibaba.fastjson.JSONObject;
import com.jourwon.spring.boot.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 消息生产者
 *
 * @author JourWon
 * @date 2021/4/30
 */
@Component
@Slf4j
public class KafkaProvider {

    /**
     * 消息 TOPIC
     */
    private static final String TOPIC = "JourWon";

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(long orderId, String orderNum, LocalDateTime createTime) {
        // 构建一个订单类
        Order order = Order.builder()
                .orderId(orderId)
                .orderNum(orderNum)
                .createTime(createTime)
                .build();

        // 发送消息，订单类的 json 作为消息体
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(TOPIC, JSONObject.toJSONString(order));

        // 监听回调
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.info("## Send message fail ...");
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("## Send message success ...");
            }
        });
    }

}
