package com.msa.plan.controller;

import com.msa.plan.dto.NotificationResponseDTO;
import com.msa.plan.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "알림 관리", description = "요금제 변경 알림 조회 API")
@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @Operation(summary = "요금제 변경 알림 조회")
    @GetMapping("/plan-changes")
    public List<NotificationResponseDTO> getPlanChangeNotifications(@RequestParam String userId) {
        return notificationService.getPlanChangeNotifications(userId);
    }
}
