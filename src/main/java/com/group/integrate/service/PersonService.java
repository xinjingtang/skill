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

    @Cacheable(keyGenerator = "keyGenerator")
    public PersonDTO selectPersonById(int id){
        PersonDTO person = personMapper.selectByPersonId(id);
        return person;
    }


}
