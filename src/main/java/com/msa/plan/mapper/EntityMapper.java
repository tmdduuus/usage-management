package com.msa.plan.mapper;

import com.msa.plan.domain.Plan;
import com.msa.plan.domain.PlanUsage;
import com.msa.plan.domain.Notification;
import com.msa.plan.entity.PlanEntity;
import com.msa.plan.entity.PlanUsageEntity;
import com.msa.plan.entity.NotificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    @Mapping(source = "planId", target = "id")
    Plan toDomain(PlanEntity entity);

    @Mapping(source = "id", target = "planId")
    PlanEntity toEntity(Plan domain);

    PlanUsage toDomain(PlanUsageEntity entity);
    PlanUsageEntity toEntity(PlanUsage domain);

    Notification toDomain(NotificationEntity entity);
    NotificationEntity toEntity(Notification domain);
}

