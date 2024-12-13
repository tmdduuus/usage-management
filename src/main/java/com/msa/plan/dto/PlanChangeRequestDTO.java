package com.msa.plan.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class PlanChangeRequestDTO {
    @NotBlank(message = "요금제 ID는 필수입니다.")
    private String planId;
}
