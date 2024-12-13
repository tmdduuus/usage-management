package com.msa.plan.repository;

import com.msa.plan.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPlanRepository extends JpaRepository<PlanEntity, String> {
}
