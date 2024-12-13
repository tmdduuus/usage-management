package com.msa.plan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "PLAN_USAGES")
@Getter
@Setter
public class PlanUsageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String usageId;
    private String userId;
    private String planId;
    private String yearMonth;
    private int totalDataAmount;
    private int usedDataAmount;
    private int remainingDataAmount;
    private LocalDate startDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
