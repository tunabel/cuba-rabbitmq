package com.company.rabbitmq.service;

import com.company.rabbitmq.config.RabbitMQConfig;
import com.company.rabbitmq.entity.Lender;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(RabbitSenderService.NAME)
public class RabbitSenderServiceBean implements RabbitSenderService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value(RabbitMQConfig.CUBAEXCHANGE)
    private String exchange;

    @Override
    public String send(Lender lender) {
        rabbitTemplate.convertAndSend(exchange, null, lender);
        return lender.toString();
    }

}