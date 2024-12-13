package com.msa.plan.listener;

import com.msa.plan.event.PlanChangeEvent;
import com.msa.plan.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlanChangeEventListener {
    private final NotificationService notificationService;

    @Async("notificationExecutor")
    @EventListener
    public void handlePlanChangeEvent(PlanChangeEvent event) {
        log.info("Handling plan change event for user: {}", event.getUserId());
        // Handle notification logic
    }
}
