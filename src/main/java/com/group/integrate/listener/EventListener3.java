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
@Order(1)
public class EventListener3 implements ApplicationListener<Event> {
    @Override
    @Async
    public void onApplicationEvent(Event event) {
        Person person = event.getPerson();
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("监听器3中。。。"+person);
    }
}
