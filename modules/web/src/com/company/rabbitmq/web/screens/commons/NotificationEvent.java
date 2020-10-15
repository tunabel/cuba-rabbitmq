package com.company.rabbitmq.web.screens.commons;

import com.haulmont.cuba.gui.events.UiEvent;
import org.springframework.context.ApplicationEvent;

public class NotificationEvent extends ApplicationEvent implements UiEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */

    private String message;

    public NotificationEvent(Object source) {
        super(source);
    }

    public NotificationEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
