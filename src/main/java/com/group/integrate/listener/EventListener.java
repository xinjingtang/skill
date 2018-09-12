package com.group.integrate.listener;

import com.group.integrate.domain.Person;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author xinjing.tang
 * @since 2018/9/12.
 */
@Component
//@Async
@Order(2)
public class EventListener implements ApplicationListener<Event> {
    @Override
    public void onApplicationEvent(Event event) {
        Person person = event.getPerson();
        System.out.println("监听器中。。。"+person);
    }
}
