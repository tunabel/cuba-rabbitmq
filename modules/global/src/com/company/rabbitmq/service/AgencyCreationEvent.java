package com.company.rabbitmq.service;

import com.haulmont.addon.globalevents.GlobalApplicationEvent;
import com.haulmont.addon.globalevents.GlobalUiEvent;

public class AgencyCreationEvent extends GlobalApplicationEvent implements GlobalUiEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public AgencyCreationEvent(Object source) {
        super(source);
    }

    private String message;

    public AgencyCreationEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

