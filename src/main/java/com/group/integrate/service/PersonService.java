package com.group.integrate.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.group.integrate.domain.Person;
import com.group.integrate.dto.PersonDTO;
import com.group.integrate.mapper.PersonMapper;
import com.group.integrate.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import org.springframework.data.redis.core.TimeToLive;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
@CacheConfig(cacheNames = "test")
public class PersonService extends ServiceImpl<PersonMapper,Person> implements PersonServiceI{

    Logger log = LoggerFactory.getLogger("cons");

    @Autowired
    PersonMapper personMapper;
    @Autowired
    Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;



    @Cacheable(keyGenerator = "keyGenerator")
    @TimeToLive
    public Person selectPerson(int id) {

        Person person = this.selectById(id);
        String personString = null;
        try {
            personString = jackson2ObjectMapperBuilder.build().writeValueAsString(person);
            log.info("personString {}",personString);
        } catch (JsonProcessingException e) {
            log.info("",e);
        }

        return person;
    }

    @Cacheable(keyGenerator = "keyGenerator")
    public PersonDTO selectPersonById(int id){
        PersonDTO person = personMapper.selectByPersonId(id);
        return person;
    }


    public Page<Person> selectPersonsByPage(Pageable pageable) {
        Page mybatiesPage = PageUtil.getMyBatisPage(pageable);
        List<Person> persons = personMapper.selectPersonsByPage(mybatiesPage);
        mybatiesPage.setRecords(persons);
        return mybatiesPage;
    }

    public void createPerson(int count) {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Person person = new Person();
//            person.setId(i+2);
            person.setAge(10);
            person.setName("tiantian");
            people.add(person);
        }
        this.insertBatch(people,10);
    }
}
