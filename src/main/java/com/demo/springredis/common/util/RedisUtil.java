package com.demo.springredis.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @SuppressWarnings("rawtypes")
    private static RedisTemplate redisTemplate;


    @Autowired
    @SuppressWarnings("rawtypes")
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

    public static void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 默认的时间单位为 秒
     *
     * @param key
     * @param value
     * @param timeout
     */
    public static void set(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }


    public static boolean setnx(String key, Object value,Long timeout){

        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            if (timeout > 0) {
                return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            } else {
                return true;
            }
        }

        return false;
    }



    /**
     * 默认的时间单位为 秒
     *
     * @param key
     * @param value
     * @param seconds
     */
    public static String takePlaceHolder(final String key, final String value, final long seconds) {
        return String.valueOf(redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                Object nativeConnection = connection.getNativeConnection();
                String result = null;

                // 单机模式
                if (nativeConnection instanceof Jedis) {
                    result = ((Jedis) nativeConnection).set(key, value, "NX", "EX", seconds);
                }
                return result;
            }
        }));
    }

    public static Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    public  static  void batchDeleteKey(List<String> keys) {
        redisTemplate.delete(keys);
    }

    public static void deleteKey(String key) {
        redisTemplate.delete(key);
    }
}

