package com.group.integrate.resource;

import com.group.integrate.domain.Person;
import com.group.integrate.dto.PersonDTO;
import com.group.integrate.excepiton.BizException;
import com.group.integrate.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author xinjing.tang
 * @since 2018/8/14.
 */
@RestController
public class PersonResource {

    @Autowired
    private PersonService personService;

    @RequestMapping("/show/person/{id}")
     public ResponseEntity<Person> selectPerson (@PathVariable int id) throws URISyntaxException {

        Logger logger = LoggerFactory.getLogger("FILE");
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.created(new URI("/showPerson/" +id)).body(personService.selectPerson(id));
     }

    @RequestMapping("/show/person/mapper/{id}")
    public ResponseEntity<PersonDTO> selectPersonById (@PathVariable int id) throws URISyntaxException {

        Logger logger = LoggerFactory.getLogger("FILE");
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.created(new URI("/show/person/mapper/" + id)).body(personService.selectPersonById(id));
    }


    @RequestMapping("/show/exception")
    public ResponseEntity<Person> excepition (@RequestParam(required = false) Boolean exception ) throws URISyntaxException {
        if (exception == null ? true : exception){
            throw new BizException("ceshi");
        }

        return ResponseEntity.created(new URI("/show/exception" )).body(personService.selectPerson(1));
    }
    @RequestMapping("/redis/getKeyValue")
    public String setKeyValue (@RequestParam String key,@RequestParam String value){
        Boolean success = personService.setRedisTemplate(key ,value);
        System.out.println(success);
        return personService.getRedisValue(key);
    }

}
