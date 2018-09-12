package com.group.integrate.resource;

import com.baomidou.mybatisplus.plugins.Page;
import com.group.integrate.domain.Person;
import com.group.integrate.dto.PersonDTO;
import com.group.integrate.listener.Event;
import com.group.integrate.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
    @Autowired
    private ApplicationEventPublisher publisher;

    /**
     * 接口方法上通过注解写sql
     * @param id
     * @return Person
     * @throws URISyntaxException
     */
    @RequestMapping("/show/person/{id}")
     public ResponseEntity<Person> selectPerson (@PathVariable int id) throws URISyntaxException {

        Logger logger = LoggerFactory.getLogger("FILE");
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Person person = personService.selectPerson(id);
        publisher.publishEvent(new Event(this,person));
        return ResponseEntity.created(new URI("/showPerson/" +id)).body(person);
     }

    /**
     * 创建person
     * @param count
     */
    @RequestMapping("/createPerson")
     public void createPerson(@RequestParam int count){
        personService.createPerson(count);
     }

    /**
     * 联表查询，一个person多个job
     * @param id
     * @return PersonDTO
     * @throws URISyntaxException
     */
    @RequestMapping("/show/person/mappe/{id}")
    public ResponseEntity<PersonDTO> selectPersonById (@PathVariable int id) throws URISyntaxException {

        Logger logger = LoggerFactory.getLogger("FILE");
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.created(new URI("/show/person/mapper/" + id)).body(personService.selectPersonById(id));
    }

    /**
     * findBypPage
     * @param pageable
     * @return
     * @throws URISyntaxException
     */
    @RequestMapping("/selectPersonsByPage")
    public ResponseEntity<List<Person>> selectPersonsByPage(Pageable pageable) throws URISyntaxException {
        Page<Person> page = personService.selectPersonsByPage(pageable);
        return (ResponseEntity.created(new URI("/show/person/mapper/") ).body( page.getRecords()));
    }


}
