package com.akshai.NotificationMS.Service;

import com.akshai.NotificationMS.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {

    List<NotificationDTO> getAllNotifications();

    List<NotificationDTO> getNotificationsByRecipient(String recipient);

    NotificationDTO getNotificationById(Long id);

    NotificationDTO sendNotification(NotificationDTO notificationDTO);

    void deleteNotification(Long id);
}
