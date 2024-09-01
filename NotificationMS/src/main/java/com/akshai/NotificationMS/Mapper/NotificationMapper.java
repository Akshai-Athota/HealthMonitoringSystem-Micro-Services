package com.akshai.NotificationMS.Mapper;

import com.akshai.NotificationMS.Entities.NotificationLog;
import com.akshai.NotificationMS.dto.NotificationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationDTO toDTO(NotificationLog notificationLog);

    NotificationLog toEntity(NotificationDTO dto);
}
