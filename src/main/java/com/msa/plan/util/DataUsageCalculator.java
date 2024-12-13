package com.msa.plan.util;

import lombok.experimental.UtilityClass;
import java.math.BigDecimal;
import java.math.RoundingMode;

@UtilityClass
public class DataUsageCalculator {
    
    public static int calculateRemainingData(int totalAmount, int usedAmount) {
        return Math.max(0, totalAmount - usedAmount);
    }
    
    public static double calculateUsagePercentage(int usedAmount, int totalAmount) {
        if (totalAmount == 0) return 0.0;
        return BigDecimal.valueOf(usedAmount)
                .divide(BigDecimal.valueOf(totalAmount), 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .doubleValue();
    }
}
