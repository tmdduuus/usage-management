package com.msa.plan.domain;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Plan {
    private String id;
    private String name;
    private int monthlyFee;
    private int dataAmount;
    private String description;
    private LocalDate startDate;
}
