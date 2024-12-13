package com.msa.plan.service;

import com.msa.plan.dto.NotificationResponseDTO;
import com.msa.plan.mapper.NotificationMapper;
import com.msa.plan.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Transactional(readOnly = true)
    public List<NotificationResponseDTO> getPlanChangeNotifications(String userId) {
        return notificationRepository.getNotifications(userId).stream()
                .map(notificationMapper::toDto)
                .toList();
    }
}
