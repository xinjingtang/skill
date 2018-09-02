package com.group.integrate.resource;

import com.group.integrate.service.PersonService;
import com.group.integrate.service.RedisService;
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
    RedisService redisService;

    @RequestMapping("/redis/getKeyValue")
    public String setKeyValue (@RequestParam String key, @RequestParam String value){
        Boolean success = redisService.setRedisTemplate(key ,value);
        System.out.println(success);
        return redisService.getRedisValue(key);
    }
}
