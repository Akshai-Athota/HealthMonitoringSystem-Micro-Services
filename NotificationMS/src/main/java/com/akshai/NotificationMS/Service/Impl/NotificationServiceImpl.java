package com.akshai.NotificationMS.Service.Impl;

import com.akshai.NotificationMS.Entities.NotificationLog;
import com.akshai.NotificationMS.Mapper.NotificationMapper;
import com.akshai.NotificationMS.Repository.NotificationRepository;
import com.akshai.NotificationMS.Service.NotificationService;
import com.akshai.NotificationMS.dto.NotificationDTO;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private NotificationRepository notificationRepository;
    private NotificationMapper notificationMapper;
    private JavaMailSender mailSender;

    @Override
    public List<NotificationDTO> getAllNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(notificationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> getNotificationsByRecipient(String recipient) {
        return notificationRepository.findByRecipient(recipient)
                .stream()
                .map(notificationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDTO getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .map(notificationMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
    }

    @Override
    @Retry(name = "notificationService",fallbackMethod = "sendNotificationFallback")
    public NotificationDTO sendNotification(NotificationDTO notificationDTO) {

        if ("Email".equalsIgnoreCase(notificationDTO.getNotificationType())) {
            sendEmail(notificationDTO.getRecipient(), "Notification", notificationDTO.getMessage());
        }

        notificationDTO.setSentAt(new Date());
        notificationDTO.setStatus("Sent");

        NotificationLog log = notificationMapper.toEntity(notificationDTO);
        NotificationLog savedLog = notificationRepository.save(log);
        return notificationMapper.toDTO(savedLog);
    }

    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    public NotificationDTO sendNotificationFallback(NotificationDTO notificationDTO, Throwable throwable) {
        log.error("Failed to send notification: {}", throwable.getMessage());
        notificationDTO.setStatus("Failed");
        return notificationDTO;
    }
}
