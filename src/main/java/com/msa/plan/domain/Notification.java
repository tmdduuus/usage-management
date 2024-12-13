package com.msa.plan.domain;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Notification {
    private String id;
    private String userId;
    private String message;
    private LocalDateTime sentTime;
    private boolean isRead;
}
