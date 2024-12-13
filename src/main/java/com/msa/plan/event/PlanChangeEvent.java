package com.msa.plan.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlanChangeEvent {
    private String userId;
    private String oldPlanId;
    private String newPlanId;
    private String status;
}
