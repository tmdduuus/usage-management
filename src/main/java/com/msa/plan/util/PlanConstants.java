package com.msa.plan.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PlanConstants {
    public static final String CACHE_KEY_PREFIX = "plan:";
    public static final String USAGE_CACHE_KEY_PREFIX = CACHE_KEY_PREFIX + "usage:";
    public static final String LIST_CACHE_KEY = CACHE_KEY_PREFIX + "list";
    public static final int CACHE_TTL_HOURS = 24;
    
    public static final String CHANGE_STATUS_SUCCESS = "SUCCESS";
    public static final String CHANGE_STATUS_FAILED = "FAILED";
    public static final String CHANGE_STATUS_ERROR = "ERROR";
    
    public static final String NOTIFICATION_TYPE_PLAN_CHANGE = "PLAN_CHANGE";
}
