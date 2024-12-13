package com.msa.plan.repository;

import com.msa.plan.domain.Plan;
import com.msa.plan.domain.PlanUsage;
import java.util.List;

public interface PlanCacheRepository {
    PlanUsage getPlanUsage(String yearMonth);
    List<Plan> getPlanList();
    void savePlanUsage(String yearMonth, PlanUsage data);
    void savePlanList(List<Plan> data);
}
