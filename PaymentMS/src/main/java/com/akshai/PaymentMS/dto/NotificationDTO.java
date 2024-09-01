package com.akshai.PaymentMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {

    private Long id;
    private String recipient;
    private String message;
    private String notificationType;
    private Date sentAt;
    private String status;
}