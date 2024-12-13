package com.msa.plan.service;

import com.msa.plan.domain.Plan;
import com.msa.plan.domain.PlanUsage;
import com.msa.plan.dto.PlanChangeRequestDTO;
import com.msa.plan.dto.PlanChangeResultDTO;
import com.msa.plan.mapper.PlanMapper;
import com.msa.plan.repository.PlanCacheRepository;
import com.msa.plan.repository.PlanRepository;
import com.msa.plan.gateway.MessageQueueGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlanServiceTest {
    @Mock
    private PlanRepository planRepository;
    
    @Mock
    private PlanCacheRepository planCacheRepository;
    
    @Mock
    private MessageQueueGateway messageQueueGateway;
    
    @Mock
    private PlanMapper planMapper;
    
    @InjectMocks
    private PlanService planService;
    
    private Plan testPlan;
    private PlanUsage testPlanUsage;
    
    @BeforeEach
    void setUp() {
        testPlan = new Plan();
        testPlan.setId("plan1");
        testPlan.setName("Basic Plan");
        testPlan.setMonthlyFee(10000);
        testPlan.setDataAmount(5000);
        
        testPlanUsage = new PlanUsage();
        testPlanUsage.setPlanId("plan1");
        testPlanUsage.setTotalDataAmount(5000);
        testPlanUsage.setUsedDataAmount(1000);
        testPlanUsage.setRemainingDataAmount(4000);
    }
    
    @Test
    void getPlanUsage_WhenCacheHit_ReturnsFromCache() {
        String yearMonth = "202312";
        when(planCacheRepository.getPlanUsage(yearMonth)).thenReturn(testPlanUsage);
        
        planService.getPlanUsage(yearMonth);
        
        verify(planCacheRepository).getPlanUsage(yearMonth);
        verify(planRepository, never()).getPlanUsage(yearMonth);
    }
    
    @Test
    void changePlan_WhenValidRequest_ReturnsSuccess() {
        PlanChangeRequestDTO request = new PlanChangeRequestDTO();
        request.setPlanId("plan2");
        
        when(planRepository.validatePlan(anyString(), anyString())).thenReturn(true);
        
        PlanChangeResultDTO result = planService.changePlan(request);
        
        assertEquals("SUCCESS", result.getStatus());
        verify(messageQueueGateway).sendPlanChangeNotification(anyString(), eq("plan2"));
    }
}
