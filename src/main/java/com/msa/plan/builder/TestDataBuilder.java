package com.msa.plan.builder;

import com.msa.plan.domain.Plan;
import com.msa.plan.domain.PlanUsage;
import java.time.LocalDate;

public class TestDataBuilder {
    
    public static Plan createTestPlan() {
        Plan plan = new Plan();
        plan.setId("test-plan-1");
        plan.setName("Test Basic Plan");
        plan.setMonthlyFee(10000);
        plan.setDataAmount(5000);
        plan.setDescription("Test Plan Description");
        plan.setStartDate(LocalDate.now());
        return plan;
    }
    
    public static PlanUsage createTestPlanUsage() {
        PlanUsage usage = new PlanUsage();
        usage.setPlanId("test-plan-1");
        usage.setTotalDataAmount(5000);
        usage.setUsedDataAmount(1000);
        usage.setRemainingDataAmount(4000);
        usage.setUsageDate(LocalDate.now());
        return usage;
    }
}