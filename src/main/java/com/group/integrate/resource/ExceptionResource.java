package com.group.integrate.resource;

import com.group.integrate.domain.Person;
import com.group.integrate.excepiton.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
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
 * @since 2018/8/29.
 */
@RestController
public class ExceptionResource {

    @Autowired
    private  static ApplicationContext applicationContext ;

    @RequestMapping("/show/exception")
    public ResponseEntity<Person> excepition (@RequestParam(required = false) Boolean exception ) throws URISyntaxException {

        if (exception == true ){
            throw new BizException("ceshi");
        }

        return ResponseEntity.created(new URI("/show/exception" )).body(null);
    }


}
