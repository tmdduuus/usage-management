package com.msa.plan.service;

import com.msa.plan.domain.Plan;
import com.msa.plan.domain.PlanUsage;
import com.msa.plan.dto.*;
import com.msa.plan.mapper.PlanMapper;
import com.msa.plan.repository.PlanRepository;
import com.msa.plan.repository.PlanCacheRepository;
import com.msa.plan.gateway.MessageQueueGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;
    private final PlanCacheRepository planCacheRepository;
    private final MessageQueueGateway messageQueueGateway;
    private final PlanMapper planMapper;

    @Transactional(readOnly = true)
    public PlanUsageResponseDTO getPlanUsage(String yearMonth) {
        // Try cache first
        PlanUsage cachedUsage = planCacheRepository.getPlanUsage(yearMonth);
        if (cachedUsage != null) {
            return planMapper.toUsageDto(cachedUsage);
        }

        // If not in cache, get from repository
        PlanUsage usage = planRepository.getPlanUsage(yearMonth);
        if (usage != null) {
            planCacheRepository.savePlanUsage(yearMonth, usage);
            return planMapper.toUsageDto(usage);
        }

        throw new IllegalStateException("요금제 사용량 정보를 찾을 수 없습니다.");
    }

    @Transactional(readOnly = true)
    public List<PlanListResponseDTO> getPlanList() {
        // Try cache first
        List<Plan> cachedPlans = planCacheRepository.getPlanList();
        if (cachedPlans != null && !cachedPlans.isEmpty()) {
            return planMapper.toPlanListDto(cachedPlans);
        }

        // If not in cache, get from repository
        List<Plan> plans = planRepository.getPlanList();
        if (!plans.isEmpty()) {
            planCacheRepository.savePlanList(plans);
            return planMapper.toPlanListDto(plans);
        }

        throw new IllegalStateException("요금제 목록을 찾을 수 없습니다.");
    }

    @Transactional
    public PlanChangeResultDTO changePlan(PlanChangeRequestDTO request) {
        // Validate plan change request
        if (!planRepository.validatePlan(request.getUserId(), request.getPlanId())) {
            return PlanChangeResultDTO.builder()
                    .planId(request.getPlanId())
                    .status("FAILED")
                    .message("유효하지 않은 요금제 변경 요청입니다.")
                    .build();
        }

        try {
            // Update plan
            planRepository.updatePlan(request.getUserId(), request.getPlanId());

            // Send notification
            messageQueueGateway.sendPlanChangeNotification(request.getUserId(), request.getPlanId());

            // Clear cache
            planCacheRepository.getPlanUsage(null); // Invalidate cache

            return PlanChangeResultDTO.builder()
                    .planId(request.getPlanId())
                    .status("SUCCESS")
                    .message("요금제가 성공적으로 변경되었습니다.")
                    .build();

        } catch (Exception e) {
            return PlanChangeResultDTO.builder()
                    .planId(request.getPlanId())
                    .status("ERROR")
                    .message("요금제 변경 중 오류가 발생했습니다: " + e.getMessage())
                    .build();
        }
    }
}
