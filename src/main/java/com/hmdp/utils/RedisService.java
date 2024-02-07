package com.hmdp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 用来适配多包后redis Key问题专用
 * 根据配置文件修改对应的key
 */
@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public Set<String> keysPrefix(String pattern) {
        return stringRedisTemplate.keys(pattern + "*");
    }

    public Set<String> keysSuffix(String pattern) {
        return stringRedisTemplate.keys("*" + pattern);
    }

    public Long decryBy(String key) {
        return stringRedisTemplate.opsForValue().decrement(key, 1L);
    }

    /**
     * String
     */
    public void set(String key, String value, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public Long ttl(String key) {
        return stringRedisTemplate.getExpire(key);
    }

    public Boolean setExpire(String key, long timeout, TimeUnit unit) {
        return stringRedisTemplate.expire(key, timeout, unit);
    }

    public Long getExpire(String key) {
        return stringRedisTemplate.getExpire(key);
    }

    public Boolean setExpireAt(String key, Date expireAt) {
        return stringRedisTemplate.expireAt(key, expireAt);
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public Boolean setIfNotExist(String key, String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    public Long increment(String key, long val) {
        return stringRedisTemplate.opsForValue().increment(key, val);
    }

    /**
     * hash
     */
    public void hset(String key, Object field, Object value) {
        stringRedisTemplate.opsForHash().put(key, field, value);
    }

    public void hset(String key, Map<String, Object> map) {
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    public Object hget(String key, Object field) { //返回哈希表key中给定域field的值
        return stringRedisTemplate.opsForHash().get(key, field);
    }

    public void hdel(String key, Object... fields) {

        stringRedisTemplate.opsForHash().delete(key, fields);
    }

    public Map<Object, Object> entries(String key) { //返回哈希表键值对

        return stringRedisTemplate.opsForHash().entries(key);
    }

    public Long hincrement(String key, Object field, long var3) {
        return stringRedisTemplate.opsForHash().increment(key, field, var3);
    }

    public double hincrement(String key, Object field, double var3) {
        return stringRedisTemplate.opsForHash().increment(key, field, var3);
    }

    /**
     * list
     */
    public Long lpush(String key, String value) {
        return stringRedisTemplate.opsForList().leftPush(key, value);
    }

    public Long lpushAll(String key, List<String> value) {
        return stringRedisTemplate.opsForList().leftPushAll(key, value);
    }

    public Long lSize(String key) {
        return stringRedisTemplate.opsForList().size(key);
    }

    public Long rpush(String key, String value) {
        return stringRedisTemplate.opsForList().rightPush(key, value);
    }

    public String lpop(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }


    public String rpop(String key) {
        return stringRedisTemplate.opsForList().rightPop(key);
    }

    public List<String> lrange(String key, long start, long end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }

    public List<String> zList(String key, long start, long end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }

    public Long lrem(String key, String value) {

        return stringRedisTemplate.opsForList().remove(key, 1, value);
    }

    /**
     * zset
     */
    public void zadd(String key, String value, double score) {
        stringRedisTemplate.opsForZSet().add(key, value, score);
    }

    public void zadd(String key, Set<ZSetOperations.TypedTuple<String>> values) {
        stringRedisTemplate.opsForZSet().add(key, values);
    }

    public Long zcount(String key, double score1, double score2) {
        return stringRedisTemplate.opsForZSet().count(key, score1, score2);
    }

    public Long zcard(String key) { //返回集合中元素个数
        return stringRedisTemplate.opsForZSet().zCard(key);
    }

    public Set<String> zrangeByScore(String key, double score1, double score2) {
        return stringRedisTemplate.opsForZSet().rangeByScore(key, score1, score2);
    }

    public Long zremRangeByScore(String key, double score1, double score2) {
        return stringRedisTemplate.opsForZSet().removeRangeByScore(key, score1, score2);
    }

    public Set<String> zrange(String key, long start, long end) {
        return stringRedisTemplate.opsForZSet().range(key, start, end);
    }

    public Long zRemove(String key, Object value) {
        return stringRedisTemplate.opsForZSet().remove(key, value);
    }

    /**
     * Set
     */
    public Long sadd(String key, String value) {
        return stringRedisTemplate.opsForSet().add(key, value);
    }

    public Long sadd(String key, String[] value) {
        return stringRedisTemplate.opsForSet().add(key, value);
    }

    public Long srem(String key, String value) {
        return stringRedisTemplate.opsForSet().remove(key, value);
    }

    public Long srem(String key, Object[] value) {
        return stringRedisTemplate.opsForSet().remove(key, value);
    }

    public Long incrBy(String key) {
        return stringRedisTemplate.opsForValue().increment(key, 1L);
    }

    public Long incr(String key, Long num) {
        return stringRedisTemplate.opsForValue().increment(key, num);
    }

    public Set<String> members(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    public Boolean isMember(String key, String member) {
        return stringRedisTemplate.opsForSet().isMember(key, member);
    }

    public Long ssize(String key) {
        return stringRedisTemplate.opsForSet().size(key);
    }

    public String randomMember(String key) {
        return stringRedisTemplate.opsForSet().randomMember(key);
    }

    public DataType getType(String key) {
        return stringRedisTemplate.type(key);
    }

}
