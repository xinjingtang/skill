package com.group.integrate.listener;

import com.group.integrate.domain.Person;
import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author xinjing.tang
 * @since 2018/9/12.
 */
public class Event extends ApplicationEvent {

    private Person person;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public Event(Object source,Person person) {
        super(source);
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
