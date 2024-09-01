package com.akshai.NotificationMS.Controller;

import com.akshai.NotificationMS.Service.NotificationService;
import com.akshai.NotificationMS.dto.NotificationDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class NotificationController {
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        log.info("Fetching all notifications");
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable Long id) {
        log.info("Fetching notification with id: {}", id);
        return ResponseEntity.ok(notificationService.getNotificationById(id));
    }

    @GetMapping("/recipient/{recipient}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByRecipient(@PathVariable String recipient) {
        log.info("Fetching notifications for recipient: {}", recipient);
        return ResponseEntity.ok(notificationService.getNotificationsByRecipient(recipient));
    }

    @PostMapping
    public ResponseEntity<NotificationDTO> sendNotification(@RequestBody NotificationDTO notificationDTO) {
        log.info("Sending notification to: {}", notificationDTO.getRecipient());
        return ResponseEntity.ok(notificationService.sendNotification(notificationDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        log.info("Deleting notification with id: {}", id);
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
