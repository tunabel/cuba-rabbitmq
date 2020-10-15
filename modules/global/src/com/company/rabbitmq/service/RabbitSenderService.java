package com.company.rabbitmq.service;

import com.company.rabbitmq.entity.Lender;
import org.springframework.stereotype.Service;

@Service
public interface RabbitSenderService {
    String NAME = "rabbitmq_RabbitSenderService";

    String send(Lender lender);

}