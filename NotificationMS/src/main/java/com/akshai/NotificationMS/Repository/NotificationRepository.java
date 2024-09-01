package com.akshai.NotificationMS.Repository;

import com.akshai.NotificationMS.Entities.NotificationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationLog,Long> {

    List<NotificationLog> findByRecipient(String recipient);
}
