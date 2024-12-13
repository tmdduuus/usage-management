package com.msa.plan.util;

import lombok.experimental.UtilityClass;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateTimeUtils {
    private static final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");
    
    public static String getCurrentYearMonth() {
        return YearMonth.now().format(YEAR_MONTH_FORMATTER);
    }
    
    public static boolean isValidYearMonth(String yearMonth) {
        try {
            YearMonth.parse(yearMonth, YEAR_MONTH_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static LocalDate getStartDateOfMonth(String yearMonth) {
        return YearMonth.parse(yearMonth, YEAR_MONTH_FORMATTER).atDay(1);
    }
}
