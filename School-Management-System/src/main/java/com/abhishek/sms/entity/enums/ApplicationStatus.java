package com.abhishek.sms.entity.enums;

import lombok.Getter;

@Getter
public enum ApplicationStatus {
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected");

    private final String status;

    ApplicationStatus(String status) { this.status = status; }
}
