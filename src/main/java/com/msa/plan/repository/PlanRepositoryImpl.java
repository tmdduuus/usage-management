package com.msa.plan.repository;

import com.msa.plan.domain.Plan;
import com.msa.plan.domain.PlanUsage;
import com.msa.plan.entity.PlanEntity;
import com.msa.plan.entity.PlanUsageEntity;
import com.msa.plan.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlanRepositoryImpl implements PlanRepository {
    private final JpaPlanRepository jpaPlanRepository;
    private final JpaPlanUsageRepository jpaPlanUsageRepository;
    private final EntityMapper entityMapper;

    @Override
    @Transactional(readOnly = true)
    public PlanUsage getPlanUsage(String yearMonth) {
        return jpaPlanUsageRepository.findByUserIdAndYearMonth("currentUser", yearMonth)
                .map(entityMapper::toDomain)
                .orElseThrow(() -> new IllegalStateException("사용량 정보를 찾을 수 없습니다."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Plan> getPlanList() {
        return jpaPlanRepository.findAll().stream()
                .map(entityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void updatePlan(String userId, String planId) {
        PlanEntity planEntity = jpaPlanRepository.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 요금제입니다."));

        PlanUsageEntity usageEntity = new PlanUsageEntity();
        usageEntity.setUserId(userId);
        usageEntity.setPlanId(planId);
        usageEntity.setTotalDataAmount(planEntity.getDataAmount());
        usageEntity.setUsedDataAmount(0);
        usageEntity.setRemainingDataAmount(planEntity.getDataAmount());

        jpaPlanUsageRepository.save(usageEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validatePlan(String userId, String planId) {
        return jpaPlanRepository.findById(planId).isPresent();
    }
}
