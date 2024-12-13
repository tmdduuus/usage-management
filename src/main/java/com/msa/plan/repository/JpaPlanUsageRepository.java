package com.msa.plan.repository;

import com.msa.plan.entity.PlanUsageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPlanUsageRepository extends JpaRepository<PlanUsageEntity, String> {
    Optional<PlanUsageEntity> findByUserIdAndYearMonth(String userId, String yearMonth);
}
