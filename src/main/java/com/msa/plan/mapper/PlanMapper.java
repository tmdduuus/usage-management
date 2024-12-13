package com.msa.plan.mapper;

import com.msa.plan.domain.Plan;
import com.msa.plan.domain.PlanUsage;
import com.msa.plan.dto.PlanListResponseDTO;
import com.msa.plan.dto.PlanUsageResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlanMapper {
    @Mapping(source = "planId", target = "planId")
    @Mapping(source = "name", target = "planName")
    @Mapping(source = "monthlyFee", target = "monthlyFee")
    @Mapping(source = "dataAmount", target = "dataAmount")
    @Mapping(source = "description", target = "description")
    PlanListResponseDTO toListDto(Plan plan);

    List<PlanListResponseDTO> toPlanListDto(List<Plan> plans);

    @Mapping(source = "planId", target = "planId")
    @Mapping(source = "totalDataAmount", target = "totalDataAmount")
    @Mapping(source = "usedDataAmount", target = "usedDataAmount")
    @Mapping(source = "remainingDataAmount", target = "remainingDataAmount")
    PlanUsageResponseDTO toUsageDto(PlanUsage planUsage);
}
