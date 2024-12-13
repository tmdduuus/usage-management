package com.msa.plan.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NotificationResponseDTO {
    private String userId;
    private String message;
    private LocalDateTime sentTime;
    private boolean isRead;
}
