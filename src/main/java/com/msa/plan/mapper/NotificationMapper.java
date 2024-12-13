package com.msa.plan.mapper;

import com.msa.plan.domain.Notification;
import com.msa.plan.dto.NotificationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "message", target = "message")
    @Mapping(source = "sentTime", target = "sentTime")
    @Mapping(source = "isRead", target = "isRead")
    NotificationResponseDTO toDto(Notification notification);
}
