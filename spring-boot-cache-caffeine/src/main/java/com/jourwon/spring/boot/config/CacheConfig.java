package com.jourwon.spring.boot.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存配置
 * <p>
 * SpringBoot 有两种用 Caffeine 作为缓存的方式：
 * 方式一：直接引入 Caffeine 依赖，然后使用 Caffeine 方法实现缓存。
 * 方式二：引入 Caffeine 和 Spring Cache 依赖，使用 SpringCache 注解方法实现缓存。
 *
 * @author JourWon
 * @date 2021/6/12
 */
@Configuration
@EnableCaching
public class CacheConfig {

    // @Value("${spring.cache.cache-names}")
    // private String cacheNames;
    //
    // @Value("${spring.cache.caffeine.spec}")
    // private String caffeineSpec;

    /**
     * caffeineCache
     * 方式一：直接引入 Caffeine 依赖，然后使用 Caffeine 方法实现缓存。
     * 这里返回com.github.benmanes.caffeine.cache.Cache
     *
     * @return Cache<String, Object>
     */
    // @Bean
    // public Cache<String, Object> caffeineCache() {
    //     return Caffeine.newBuilder()
    //             // 设置最后一次写入或访问后经过固定时间过期
    //             .expireAfterWrite(60, TimeUnit.SECONDS)
    //             // 初始的缓存空间大小
    //             .initialCapacity(100)
    //             // 缓存的最大条数
    //             .maximumSize(1000)
    //             .build();
    // }

    // 方式二：引入 Caffeine 和 Spring Cache 依赖，使用 SpringCache 注解方法实现缓存。
    // /**
    //  * 相当于在构建LoadingCache对象的时候 build()方法中指定过期之后的加载策略方法
    //  * 必须要指定这个Bean，refreshAfterWrite=60s属性才生效
    //  *
    //  * @return CacheLoader<Object, Object>
    //  */
    // @Bean
    // public CacheLoader<Object, Object> cacheLoader() {
    //     CacheLoader<Object, Object> cacheLoader = new CacheLoader<Object, Object>() {
    //         @Override
    //         public Object load(Object key) {
    //             return null;
    //         }
    //
    //         // 重写这个方法将oldValue值返回回去，进而刷新缓存
    //         @Override
    //         public Object reload(Object key, Object oldValue) {
    //             return oldValue;
    //         }
    //     };
    //
    //     return cacheLoader;
    // }

    // /**
    //  * 缓存管理器
    //  *
    //  * @return CacheManager
    //  */
    // @Bean
    // public CacheManager cacheManager() {
    //     CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    //     cacheManager.setCaffeine(Caffeine.newBuilder()
    //             // 设置最后一次写入或访问后经过固定时间过期
    //             .expireAfterAccess(60, TimeUnit.SECONDS)
    //             // 初始的缓存空间大小
    //             .initialCapacity(100)
    //             // 缓存的最大条数
    //             .maximumSize(1000));
    //     return cacheManager;
    // }

    // /**
    //  * 缓存管理器
    //  * 这里返回org.springframework.cache.Cache
    //  *
    //  * @return CacheManager
    //  */
    // @Bean
    // public CacheManager cacheManager() {
    //     SimpleCacheManager cacheManager = new SimpleCacheManager();
    //     List<Cache> caches = new ArrayList<>();
    //     if (StringUtils.isNotBlank(cacheNames)) {
    //         Caffeine<Object, Object> caffeine = Caffeine.from(caffeineSpec);
    //         String[] cacheNamesArray = cacheNames.split(",");
    //         for (String cacheName : cacheNamesArray) {
    //             caches.add(new CaffeineCache(cacheName, caffeine.build()));
    //         }
    //     }
    //
    //     cacheManager.setCaches(caches);
    //     return cacheManager;
    // }

}
