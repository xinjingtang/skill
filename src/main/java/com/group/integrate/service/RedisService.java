package com.group.integrate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author xinjing.tang
 * @since 2018/8/29.
 */
@Service
public class RedisService {

    @Autowired
    RedisTemplate template;
    public Boolean setRedisTemplate(String key ,String value){
//        template.opsForValue().setIfAbsent()
        Object obj = template.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Boolean b = redisConnection.set(key.getBytes(),value.getBytes());
                return b;
            }
        });

        System.out.print(key);
        return obj != null ? (Boolean) obj : false;
    }

    public String getRedisValue (String key){
        Object obj = template.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                StringRedisSerializer serializer = new StringRedisSerializer();
                byte[] b = redisConnection.get(key.getBytes());
                redisConnection.close();
                if (b == null) {
                    return null;
                }
                return serializer.deserialize(b);
            }
        });
        return obj != null ? obj.toString() : null;
    }
}
