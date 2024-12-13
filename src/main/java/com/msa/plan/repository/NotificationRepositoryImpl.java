package com.msa.plan.repository;

import com.msa.plan.domain.Notification;
import com.msa.plan.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository {
    private final JpaNotificationRepository jpaNotificationRepository;
    private final EntityMapper entityMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Notification> getNotifications(String userId) {
        return jpaNotificationRepository.findByUserIdOrderBySentTimeDesc(userId).stream()
                .map(entityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void saveNotification(Notification notification) {
        jpaNotificationRepository.save(entityMapper.toEntity(notification));
    }
}
