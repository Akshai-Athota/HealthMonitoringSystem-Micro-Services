package com.akshai.PaymentMS.Publisher;

import com.akshai.PaymentMS.dto.NotificationDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class NotificationPublisher {

    private RabbitTemplate rabbitTemplate;

    public void sendEmailNotification(NotificationDTO notificationDTO){
        rabbitTemplate.convertAndSend("notification-queue",notificationDTO);
    }
}
