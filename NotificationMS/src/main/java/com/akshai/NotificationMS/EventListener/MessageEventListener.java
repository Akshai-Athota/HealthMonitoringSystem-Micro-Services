package com.akshai.NotificationMS.EventListener;

import com.akshai.NotificationMS.Service.NotificationService;
import com.akshai.NotificationMS.dto.NotificationDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class MessageEventListener {
    private NotificationService notificationService;

    @RabbitListener(queues = "notification-queue")
    public void handleNotification(NotificationDTO notificationDTO) {
        log.info("Received message for notification: {}", notificationDTO);
        notificationService.sendNotification(notificationDTO);
    }
}
