package com.msa.plan.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanChangeResultDTO {
    private String planId;
    private String status;
    private String message;
}
