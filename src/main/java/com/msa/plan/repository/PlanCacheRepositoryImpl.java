package com.msa.plan.repository;

import com.msa.plan.domain.Plan;
import com.msa.plan.domain.PlanUsage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class PlanCacheRepositoryImpl implements PlanCacheRepository {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String PLAN_USAGE_KEY_PREFIX = "plan:usage:";
    private static final String PLAN_LIST_KEY = "plan:list";
    private static final long CACHE_TTL_HOURS = 24;

    @Override
    public PlanUsage getPlanUsage(String yearMonth) {
        return (PlanUsage) redisTemplate.opsForValue()
                .get(PLAN_USAGE_KEY_PREFIX + yearMonth);
    }

    @Override
    public List<Plan> getPlanList() {
        return (List<Plan>) redisTemplate.opsForValue()
                .get(PLAN_LIST_KEY);
    }

    @Override
    public void savePlanUsage(String yearMonth, PlanUsage data) {
        redisTemplate.opsForValue()
                .set(PLAN_USAGE_KEY_PREFIX + yearMonth, data, CACHE_TTL_HOURS, TimeUnit.HOURS);
    }

    @Override
    public void savePlanList(List<Plan> data) {
        redisTemplate.opsForValue()
                .set(PLAN_LIST_KEY, data, CACHE_TTL_HOURS, TimeUnit.HOURS);
    }
}
