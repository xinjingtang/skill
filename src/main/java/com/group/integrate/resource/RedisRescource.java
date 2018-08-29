package com.group.integrate.resource;

import com.group.integrate.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author xinjing.tang
 * @since 2018/8/29.
 */
@RestController
public class RedisRescource {
    @Autowired
    PersonService personService;

    @RequestMapping("/redis/getKeyValue")
    public String setKeyValue (@RequestParam String key, @RequestParam String value){
        Boolean success = personService.setRedisTemplate(key ,value);
        System.out.println(success);
        return personService.getRedisValue(key);
    }
}
