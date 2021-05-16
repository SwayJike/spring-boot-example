package com.jourwon.spring.boot;

import com.jourwon.spring.boot.model.entity.Order;
import com.jourwon.spring.boot.service.OrderService;
import com.mysql.cj.jdbc.Driver;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 插入订单数据
 *
 * @author JourWon
 * @date 2021/5/16
 */
@Slf4j
@SpringBootTest
class SpringBootEasyexcelApplicationTests {

    @Resource
    private OrderService orderService;

    @Test
    void contextLoads() {
    }

    private static final Random OR = new Random();
    private static final Random AR = new Random();
    private static final Random DR = new Random();

    /**
     * 插入100w数据耗时:27921 ms
     */
    @Test
    public void testGenerateOrder1() {
        long start = System.currentTimeMillis();
        HikariConfig config = new HikariConfig();
        config.setUsername("root");
        config.setPassword("root");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true&useSSL=false&tinyInt1isBit=false&serverTimezone=GMT%2B8");
        config.setDriverClassName(Driver.class.getName());
        HikariDataSource hikariDataSource = new HikariDataSource(config);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);
        for (int d = 0; d < 100; d++) {
            String item = "('%s','%d','2021-05-%d 00:00:00','%d')";
            StringBuilder sql = new StringBuilder("INSERT INTO t_order(order_id,amount,payment_time,order_status) VALUES ");
            for (int i = 0; i < 10_000; i++) {
                sql.append(String.format(item, UUID.randomUUID().toString().replace("-", ""),
                        AR.nextInt(100000) + 1, DR.nextInt(31) + 1, OR.nextInt(3))).append(",");
            }
            jdbcTemplate.update(sql.substring(0, sql.lastIndexOf(",")));
        }
        hikariDataSource.close();
        log.info("插入100w数据耗时:{} ms", System.currentTimeMillis() - start);
    }

    /**
     * 插入100w数据耗时:130966 ms
     */
    @Test
    public void testGenerateOrder2() {
        long start = System.currentTimeMillis();
        List<Order> list = new ArrayList<>(10000);
        String item = "2021-05-%d 00:00:00";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (int d = 0; d < 100; d++) {
            for (int i = 0; i < 10_000; i++) {
                Order order = Order.builder().orderId(UUID.randomUUID().toString().replace("-", ""))
                        .amount(BigDecimal.valueOf(AR.nextInt(100000) + 1))
                        .paymentTime(LocalDateTime.parse(String.format(item, DR.nextInt(21) + 11), dateTimeFormatter))
                        .orderStatus(OR.nextInt(3)).build();
                list.add(order);
            }
            orderService.saveBatch(list);
            list.clear();
        }

        log.info("插入100w数据耗时:{} ms", System.currentTimeMillis() - start);
    }

}
