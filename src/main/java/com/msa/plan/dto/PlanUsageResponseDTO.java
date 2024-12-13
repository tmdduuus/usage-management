package com.msa.plan.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PlanUsageResponseDTO {
    private String planId;
    private String planName;
    private int monthlyFee;
    private LocalDate startDate;
    private int totalDataAmount;
    private int usedDataAmount;
    private int remainingDataAmount;
}

