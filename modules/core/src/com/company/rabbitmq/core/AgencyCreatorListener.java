package com.company.rabbitmq.core;

import com.company.rabbitmq.config.RabbitMQConfig;
import com.company.rabbitmq.entity.Agency;
import com.company.rabbitmq.entity.Lender;

import com.company.rabbitmq.service.AgencyCreationEvent;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Events;
import com.haulmont.cuba.security.app.Authentication;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.*;

@Component(AgencyCreatorListener.NAME)
public class AgencyCreatorListener{

    public static final String NAME = "rabbitmq_AgencyCreatorListener";

    @Inject
    private Authentication authentication;
    @Inject
    private DataManager dataManager;
    @Inject
    private Events events;

    @RabbitListener(queues = RabbitMQConfig.CUBAQUEUE, containerFactory = "inputListenerContainerFactory")
    public void receivedMessage(Lender lender) {
        authentication.begin();

        if (lender.getSize() >= 20) {

            Agency newAgency = dataManager.create(Agency.class);
            newAgency.setName("Agency"+ RandomStringUtils.randomNumeric(3));
            dataManager.commit(newAgency);
            events.publish(new AgencyCreationEvent(this, "New Agency created"));
        }
        authentication.end();
    }


    //Another option is let this Listener implements MessageListener and thus overriding this onMessage method.
    //but then we will need other way to pass Lender object to the outside
    //Remember to edit RabbitMQConfig.java
    public void onMessage(Message message) {

        authentication.begin();
        Lender receivedLender = convertMessagetoLender(message);
        authentication.end();
    }

    private Lender convertMessagetoLender(Message message) {
        ByteArrayInputStream bis = new ByteArrayInputStream(message.getBody());
        ObjectInput in = null;
        Lender lender = null;
        try {
            in = new ObjectInputStream(bis);
            lender = (Lender) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        return lender;
    }
}