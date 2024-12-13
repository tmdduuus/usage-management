package com.msa.plan.controller;

import com.msa.plan.dto.*;
import com.msa.plan.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "요금제 관리", description = "요금제 조회 및 변경 API")
@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;

    @Operation(summary = "현재 요금제 사용량 조회")
    @GetMapping("/usage")
    public PlanUsageResponseDTO getPlanUsage(@RequestParam(required = false) String yearMonth) {
        return planService.getPlanUsage(yearMonth);
    }

    @Operation(summary = "전체 요금제 목록 조회")
    @GetMapping("/list")
    public List<PlanListResponseDTO> getPlanList() {
        return planService.getPlanList();
    }

    @Operation(summary = "요금제 변경")
    @PutMapping("/change")
    public PlanChangeResultDTO changePlan(@RequestBody PlanChangeRequestDTO request) {
        return planService.changePlan(request);
    }
}
