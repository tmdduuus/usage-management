package com.msa.plan.domain;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PlanUsage {
    private String planId;
    private int totalDataAmount;
    private int usedDataAmount;
    private int remainingDataAmount;
    private LocalDate usageDate;
}
