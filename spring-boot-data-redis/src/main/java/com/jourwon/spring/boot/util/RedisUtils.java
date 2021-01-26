package com.jourwon.spring.boot.util;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author JourWon
 * @date 2021/1/18
 */
@Component
public final class RedisUtils {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private ValueOperations<String, String> valueOperations;

    @Resource
    private HashOperations<String, String, String> hashOperations;

    @Resource
    private ListOperations<String, String> listOperations;

    @Resource
    private SetOperations<String, String> setOperations;

    @Resource
    private ZSetOperations<String, String> zSetOperations;

    //
    // RedisOperations
    //

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return boolean true：存在，false：不存在
     */
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /**
     * 设置缓存过期时间(秒)
     *
     * @param key     键
     * @param timeout 过期时间
     * @return boolean
     */
    public boolean expire(String key, long timeout) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, TimeUnit.SECONDS));
    }

    /**
     * 返回键的剩余有效时间(秒)
     *
     * @param key 键 不能为null
     * @return Long 如果键不存在返回-2，如果键存在且没有设置过期时间返回-1
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 删除一个键
     *
     * @param key 键
     * @return boolean true：删除成功，false：删除失败
     */
    public boolean delete(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    /**
     * 删除多个键，返回删除键的个数
     *
     * @param keys 键
     * @return Long 删除键的个数
     */
    public Long delete(List<String> keys) {
        return redisTemplate.delete(keys);
    }

    //
    // ValueOperations
    //

    /**
     * 根据键获取对应的值
     *
     * @param key 键
     * @return String 值
     */
    public String get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 缓存键值对
     *
     * @param key   键，不能为null
     * @param value 值，不能为null
     * @return boolean true:成功,false:失败
     */
    public boolean set(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 缓存键值对，并设置过期时间
     *
     * @param key     键，不能为null
     * @param value   值，不能为null
     * @param timeout 时间(秒)，如果time小于等于0，不设置过期时间
     * @return boolean true:成功,false:失败
     */
    public boolean set(String key, String value, long timeout) {
        try {
            if (timeout > 0) {
                redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
            } else {
                this.set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增1
     *
     * @param key 键
     * @return Long 递增后的值
     */
    public Long increment(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * 递增指定的数值
     *
     * @param key   键
     * @param delta 指定的数值
     * @return Long 递增后的值
     */
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递增1
     *
     * @param key 键
     * @return Long 递增后的值
     */
    public Long decrement(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }

    /**
     * 递减指定的数值
     *
     * @param key   键
     * @param delta 指定的数值
     * @return Long 递减后的值
     */
    public Long decrement(String key, long delta) {
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    //
    // HashOperations
    //

    /**
     * 向hash表中存放一个键值对数据,如果不存在将创建
     *
     * @param key     键,不能为null
     * @param hashKey hash键,不能为null
     * @param value   值
     * @return boolean true:成功,false:失败
     */
    public boolean hset(String key, String hashKey, String value) {
        return this.hset(key, hashKey, value, 0L);
    }

    /**
     * 向hash表中存放一个键值对数据,如果不存在将创建,带过期时间(秒)
     *
     * @param key     键
     * @param hashKey hash键
     * @param value   值
     * @param timeout 过期时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return boolean true:成功,false:失败
     */
    public boolean hset(String key, String hashKey, String value, long timeout) {
        try {
            redisTemplate.<String, String>opsForHash().put(key, hashKey, value);
            if (timeout > 0) {
                expire(key, timeout);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向hash表中存放多个键值对数据
     *
     * @param key 键
     * @param map 多个键值对
     * @return boolean true:成功,false:失败
     */
    public boolean hmset(String key, Map<String, String> map) {
        try {
            redisTemplate.<String, String>opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向hash表中存放多个键值对数据,带过期时间(秒)
     *
     * @param key     键
     * @param map     对应多个键值
     * @param timeout 过期时间(秒)
     * @return boolean true:成功,false:失败
     */
    public boolean hmset(String key, Map<String, String> map, long timeout) {
        try {
            redisTemplate.<String, String>opsForHash().putAll(key, map);
            if (timeout > 0) {
                expire(key, timeout);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取hashKey对应的键值
     *
     * @param key     键 不能为null
     * @param hashKey hash键 不能为null
     * @return String hashKey对应的键值
     */
    public String hget(String key, String hashKey) {
        return redisTemplate.<String, String>opsForHash().get(key, hashKey);
    }

    /**
     * 获取key对应的所有键值
     *
     * @param key 键 不能为null
     * @return Map<String, String> key对应的所有键值
     */
    public Map<String, String> hmget(String key) {
        return redisTemplate.<String, String>opsForHash().entries(key);
    }

    /**
     * 判断hash表中是否存在hashKey对应的值
     *
     * @param key     键 不能为null
     * @param hashKey hash键 不能为null
     * @return boolean true存在 false不存在
     */
    public boolean hHasKey(String key, String hashKey) {
        return Boolean.TRUE.equals(redisTemplate.<String, String>opsForHash().hasKey(key, hashKey));
    }

    /**
     * 获取key对应hash表的所有hashKey列表
     *
     * @param key 键 不能为null
     * @return Set<String> hashKey列表
     */
    public Set<String> hKeys(String key) {
        return redisTemplate.<String, String>opsForHash().keys(key);
    }

    /**
     * 删除hash表中的值
     *
     * @param key     键 不能为null
     * @param hashKey hash键 可以是多个,不能为null
     * @return boolean true成功 false失败
     */
    public boolean hdel(String key, String... hashKey) {
        try {
            redisTemplate.<String, String>opsForHash().delete(key, hashKey);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 一次获取 hash 中多个 hashKey 的值，按 hashKey 顺序返回值
     * 如传入 hashKeys = [f1, f2, f3, f4]，其中 f3 在 hash 表中无值
     * 则返回 [f1v, f2v, f3v, null, f4v] v 表示 value
     *
     * @param key  键 不能为null
     * @param keys hash键列表,不能为null
     * @return List<String> hash表中多个键对应的值
     */
    public List<String> hMultiGet(String key, List<String> keys) {
        return redisTemplate.<String, String>opsForHash().multiGet(key, keys);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key     键
     * @param hashKey hash键
     * @param delta   递增值
     * @return Double 递增后的值
     */
    public Double hincr(String key, String hashKey, double delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    //
    // SetOperations
    //

    /**
     * 将数据放入set
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return Long 成功个数
     */
    public Long sAdd(String key, String... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return Long 成功个数
     */
    public Long sAddAndExpire(String key, long time, String... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        if (time > 0) {
            expire(key, time);
        }
        return count;
    }

    /**
     * 移除值为value的值
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return Long 移除的个数
     */

    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * set中查询指定value是否存在
     *
     * @param key   键
     * @param value 值
     * @return boolean true存在 false不存在
     */
    public boolean sIsMember(String key, String value) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, value));
    }

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return Set<String> Set集合
     */
    public Set<String> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return Long set缓存的长度
     */
    public Long sSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    //
    // ListOperations
    //

    /**
     * 在列表中设置索引index处的元素为value
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return boolean true成功,false失败
     */
    public boolean lSet(String key, long index, String value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 通过索引获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时,0表头,1第二个元素,依次类推;index<0时,-1表尾,-2倒数第二个元素,依次类推
     * @return String 值
     */
    public String lIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 将value放入list缓存
     *
     * @param key   键
     * @param value 值
     * @return Long 添加元素的个数
     */
    public Long lRightPush(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 将value放入list缓存,带过期时间(秒)
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间(秒)
     * @return Long 添加元素的个数
     */
    public Long lRightPush(String key, String value, long timeout) {
        Long count = redisTemplate.opsForList().rightPush(key, value);
        if (timeout > 0) {
            expire(key, timeout);
        }
        return count;

    }

    /**
     * 将value列表放入缓存
     *
     * @param key   键
     * @param value 列表
     * @return Long 添加元素的个数
     */
    public Long lRightPushAll(String key, List<String> value) {
        return redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 将value列表放入缓存,带过期时间(秒)
     *
     * @param key     键
     * @param value   列表
     * @param timeout 过期时间(秒)
     * @return Long 添加元素的个数
     */
    public Long lRightPushAll(String key, List<String> value, long timeout) {
        Long count = redisTemplate.opsForList().rightPushAll(key, value);
        if (timeout > 0) {
            expire(key, timeout);
        }
        return count;
    }

    /**
     * 从list中移除前count个值为value的元素
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return Long 移除的个数
     */
    public Long lRemove(String key, long count, String value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 获取list中从start到end的元素
     *
     * @param key   键
     * @param start 开始
     * @param end   结束(0 或 -1 代表所有值)
     * @return List<String> list列表
     */
    public List<String> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取list列表的长度
     *
     * @param key 键
     * @return Long list缓存的长度
     */
    public Long lGetListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /* ----------- zset --------- */
    public int zcard(String key) {
        return redisTemplate.opsForZSet().zCard(key).intValue();
    }

    /**
     * 有序集合添加
     *
     * @param key
     * @param value
     * @param scoure
     */
    public void zsset(String key, String value, double scoure) {
        redisTemplate.opsForZSet().add(key, value, scoure);
    }

    /**
     * 有序集合获取
     *
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<String> zsget(String key, double scoure, double scoure1) {
        return redisTemplate.opsForZSet().rangeByScore(key, scoure, scoure1);
    }

    /**
     * 获取所有set
     *
     * @param key
     * @return
     */
    public Set<String> zsetGetAll(String key) {
        return redisTemplate.opsForZSet().range(key, 0, -1);
    }


    /**
     * 获取范围的元素来自start于end从下令从低分到高分排序集。
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> rangeByScore(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeByScore(key, start, end);
    }


    public Double getScore(String key, String member) {
        return redisTemplate.opsForZSet().score(key, member);
    }


    /**
     * 删除zset元素
     *
     * @param key
     * @param val
     * @return
     */
    public Long zdel(String key, String... val) {
        return redisTemplate.opsForZSet().remove(key, val);
    }

}
