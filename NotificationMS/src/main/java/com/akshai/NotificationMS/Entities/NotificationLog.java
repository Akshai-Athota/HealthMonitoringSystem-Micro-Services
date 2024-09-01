package com.akshai.NotificationMS.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification_logs")
public class NotificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String recipient;  // Email, phone number, or other contact details

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String notificationType;  // e.g., Email, SMS, Push

    @Column(nullable = false)
    private Date sentAt;

    private String status;
}
