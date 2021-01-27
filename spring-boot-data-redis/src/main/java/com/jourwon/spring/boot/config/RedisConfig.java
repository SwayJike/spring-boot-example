package com.jourwon.spring.boot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * Redis配置类
 *
 * @author JourWon
 * @date 2021/1/18
 */
@Configuration
public class RedisConfig {

    @Resource
    private ObjectMapper objectMapper;

    /**
     * Redis模板
     *
     * @param factory Redis连接池
     * @return RedisTemplate<String, String> Redis模板
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        // ObjectMapper om = new ObjectMapper();
        // // 属性为NULL不序列化
        // // om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        // om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        // om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        // // 日期时间序列化处理
        // om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // om.registerModule(new Jdk8Module())
        //         .registerModule(new JavaTimeModule())
        //         .registerModule(new ParameterNamesModule());
        //
        // // 序列化时将null转成空字符串
        // om.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
        //     @Override
        //     public void serialize(Object param, JsonGenerator jsonGenerator,
        //                           SerializerProvider paramSerializerProvider) throws IOException {
        //         jsonGenerator.writeString("");
        //     }
        // });



        // SimpleModule module = new SimpleModule();
        // module.addDeserializer(String.class, new StdDeserializer<String>(String.class) {
        //     @Override
        //     public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        //         String result = StringDeserializer.instance.deserialize(p, ctxt);
        //         if (!StringUtils.hasText(result)) {
        //             return null;
        //         }
        //         return result;
        //     }
        // });
        // om.registerModule(module);
        // om.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);



        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(stringRedisSerializer);
        // template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(stringRedisSerializer);
        // template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.setDefaultSerializer(stringRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public ValueOperations<String, String> valueOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    public HashOperations<String, String, String> hashOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public ListOperations<String, String> listOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public SetOperations<String, String> setOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations<String, String> zSetOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

}
