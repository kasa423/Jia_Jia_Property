package com.fetch.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author jiang chen
 * @ClassName RedisCache
 * @date 2024/02/29 15:25
 * @description: TODO
 */

@Component
public class RedisCache {

    private RedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 缓存基本对象
     *
     * @param key   redis 键
     * @param value redis 值
     */
    public <T> void setCacheObject(@NonNull final String key, @NonNull final T value) {
        redisTemplate.opsForValue().set(key, value);
    }


    /**
     * @param key      redis键
     * @param value    redis值
     * @param timeout  过期时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置有效时间
     *
     * @param key      redis键
     * @param timeout  有效时间
     * @param timeUnit 时间颗粒度
     * @return true = 设置成功 ； false = 设置失败
     */
    public boolean expire(final String key, final Integer timeout, final TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    public boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return
     */
    public long deleteObject(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 缓存list集合对象
     *
     * @param key        redis 键
     * @param collection 值
     * @return 缓存好的对象数量
     */
    public <T> long setCacheList(final String key, final List<T> collection) {
        Long count = redisTemplate.opsForList().rightPushAll(key, collection);
        return count == null ? 0 : count;
    }

    /**
     * 获取list 数据
     *
     * @param key
     * @return list数据
     */
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存set
     *
     * @param key  redis 键
     * @param data 需要缓存的set
     * @return 缓存好的对象
     */
    public <T> BoundSetOperations<String, T> boundSet(final String key, final Set<T> data) {
        BoundSetOperations<String, T> operations = redisTemplate.boundSetOps(key);
        Iterator<T> iterator = data.iterator();
        while (iterator.hasNext()) {
            operations.add(iterator.next());
        }
        return redisTemplate.boundSetOps(key);
    }

    /**
     * 获取缓存的Set
     *
     * @param key 缓存中的键
     * @return
     */
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public <T> void setCacheMapValue(final String key, final String hashKey, final T data) {
        redisTemplate.opsForHash().put(key, hashKey, data);
    }

    public <T> T getCacheMapValue(final String key, final String hashKey) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(key, hashKey);
    }

    public void deleteCacheMapValue(final String key, final String hashKey) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, hashKey);
    }

    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hashKeys) {
        return redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> Keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }

}
