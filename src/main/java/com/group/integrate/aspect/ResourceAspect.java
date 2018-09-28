package com.group.integrate.aspect;

import com.group.integrate.util.skill.HttpRequestUtil;
import com.group.integrate.util.skill.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @author elvis.xu
 * @since 2016-06-21 10:45
 */
@Component
@Aspect
@Order(1)
public class ResourceAspect {
    public static final Logger log = LoggerFactory.getLogger(ResourceAspect.class);

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)||@annotation(org.springframework.web.bind.annotation.GetMapping)||@annotation(org.springframework.web.bind.annotation.PostMapping)||@annotation(org.springframework.web.bind.annotation.PutMapping))")
    protected Object aroudAdivce(ProceedingJoinPoint jp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        RequestContext.clear();
        HttpRequestUtil.resolveContext(request);

        String argsString = "";
        if (log.isInfoEnabled() || log.isDebugEnabled()) {
            if (Arrays.asList(jp.getArgs()).stream().filter(arg -> arg instanceof File || arg instanceof InputStream || arg instanceof InputStreamSource || arg instanceof byte[]).count() == 0) {
                argsString = Arrays.toString(jp.getArgs());
            }
        }

        log.debug("REQUEST BEGIN.......: {} , args={} ", RequestContext.getLogString(), argsString);

        Object rt = null;

        // 版本检测等公共行为

        rt = jp.proceed();

        long cost = System.currentTimeMillis() - RequestContext.getTimeStart();
        if (log.isInfoEnabled()) {
            String resStatus = "200 OK";
            if (rt instanceof ResponseEntity) {
                ResponseEntity resEnt = (ResponseEntity) rt;
                resStatus = resEnt.getStatusCode().value() + " " + resEnt.getStatusCode().getReasonPhrase();
            }


            log.info("REQUEST END  [cost={}] : {} , args={} , res={},ip={}", StringUtils.leftPad(cost + "", 5), RequestContext.getLogString(), argsString, resStatus,RequestContext.getClientIp());
        }
        RequestContext.clear();
        return rt;
    }
}
