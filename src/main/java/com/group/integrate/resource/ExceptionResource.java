package com.group.integrate.resource;

import com.group.integrate.domain.Person;
import com.group.integrate.excepiton.BizException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
public class ExceptionResource {

    @RequestMapping("/show/exception")
    public ResponseEntity<Person> excepition (@RequestParam(required = false) Boolean exception ) throws URISyntaxException {
        if (exception == null ? true : exception){
            throw new BizException("ceshi");
        }

        return ResponseEntity.created(new URI("/show/exception" )).body(null);
    }


}
