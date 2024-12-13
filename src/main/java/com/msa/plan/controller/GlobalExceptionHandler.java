package com.msa.plan.controller;

import com.msa.plan.exception.PlanNotFoundException;
import com.msa.plan.exception.PlanChangeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(PlanNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlanNotFound(PlanNotFoundException e) {
        return ResponseEntity.notFound()
                .build();
    }
    
    @ExceptionHandler(PlanChangeException.class)
    public ResponseEntity<ErrorResponse> handlePlanChangeError(PlanChangeException e) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("PLAN_CHANGE_ERROR", e.getMessage()));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericError(Exception e) {
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse("INTERNAL_ERROR", "Internal server error occurred"));
    }

    @Data
    @AllArgsConstructor
    static class ErrorResponse {
        private String code;
        private String message;
    }
}
