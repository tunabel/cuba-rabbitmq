package com.company.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    public static final String CUBAQUEUE = "cubaqueue";
    public static final String CUBAEXCHANGE = "cubaexchange";

    @Value(CUBAQUEUE)
    String queueName;

    @Value(CUBAEXCHANGE)
    String exchange;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(exchange);
    }

    @Bean
    Binding binding(Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }


    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

    @Bean(name = "inputListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new
                SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMessageConverter(new SimpleMessageConverter());
        return factory;
    }

//    create MessageListenerContainer using custom connection factory
//    @Bean
//    MessageListenerContainer messageListenerContainer() {
//        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
//        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
//        simpleMessageListenerContainer.setQueueNames(queueName);
//        simpleMessageListenerContainer.setMessageListener(agencyCreatorListener);
//        return simpleMessageListenerContainer;
//    }
//
//    @Autowired
//    AgencyCreatorListener agencyCreatorListener;

}
