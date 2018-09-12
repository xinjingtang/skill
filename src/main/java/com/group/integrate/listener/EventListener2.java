package com.group.integrate.listener;

import com.group.integrate.domain.Person;
import org.springframework.context.ApplicationListener;
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
//@Order(1)
public class EventListener2 implements ApplicationListener<Event> {
    @Override

    public void onApplicationEvent(Event event) {
        Person person = event.getPerson();
        System.out.println("监听器2中。。。"+person);
    }
}
