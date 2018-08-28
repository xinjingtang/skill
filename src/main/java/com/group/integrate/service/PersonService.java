package com.group.integrate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.group.integrate.domain.Person;
import com.group.integrate.dto.PersonDTO;
import com.group.integrate.mapper.PersonMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author xinjing.tang
 * @since 2018/8/14.
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "cacheName")
public class PersonService {

    Logger log = LoggerFactory.getLogger("cons");

    @Autowired
    PersonMapper personMapper;
    @Autowired
    Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;
    @Autowired
    RedisTemplate template;


    @Cacheable(keyGenerator = "keyGenerator")
    public Person selectPerson(int id) {

        Person person = personMapper.selectPerson(1);
        String personString = null;
        try {
            personString = jackson2ObjectMapperBuilder.build().writeValueAsString(person);
            log.info("personString {}",personString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return person;
    }

    public PersonDTO selectPersonById(int id){
        PersonDTO person = personMapper.selectByPersonId(id);
        return person;
    }

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
