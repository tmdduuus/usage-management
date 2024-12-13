package com.msa.plan.repository;

import com.msa.plan.domain.Plan;
import com.msa.plan.domain.PlanUsage;
import java.util.List;

public interface PlanRepository {
    PlanUsage getPlanUsage(String yearMonth);
    List<Plan> getPlanList();
    void updatePlan(String userId, String planId);
    boolean validatePlan(String userId, String planId);
}
