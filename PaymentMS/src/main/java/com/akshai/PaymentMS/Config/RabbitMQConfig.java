package com.akshai.PaymentMS.Config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    private Queue createNotificationQueue(){
        return new Queue("notification-queue",true);
    }
}
