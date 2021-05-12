package com.jourwon.spring.boot;

import com.jourwon.spring.boot.provider.KafkaProvider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringBootKafkaApplicationTests {

	@Test
	void contextLoads() {
	}

	@Resource
	private KafkaProvider kafkaProvider;

	@Test
	public void sendMessage() throws InterruptedException {
		// 发送 1000 个消息
		for (int i = 0; i < 1000; i++) {
			long orderId = i+1;
			String orderNum = UUID.randomUUID().toString();
			kafkaProvider.sendMessage(orderId, orderNum, LocalDateTime.now());
		}

		TimeUnit.MINUTES.sleep(1);
	}

}
