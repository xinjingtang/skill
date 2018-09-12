package com.group.integrate.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author xinjing.tang
 * @since 2018/9/12.
 */
@Slf4j
public class TestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        log.info("---------------------开始进入请求地址拦截----------------------------");
        HttpSession session = httpServletRequest.getSession();
        if(StringUtils.isEmpty(session.getAttribute("userName"))){
            return true;
        }
        else{
            PrintWriter printWriter = httpServletResponse.getWriter();
            printWriter.write("{code:0,message:\"session is invalid,please login again!\"}");
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
    }


}
