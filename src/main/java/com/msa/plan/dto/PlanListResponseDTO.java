package com.msa.plan.dto;

import lombok.Data;

@Data
public class PlanListResponseDTO {
    private String planId;
    private String planName;
    private int monthlyFee;
    private int dataAmount;
    private String description;
}
