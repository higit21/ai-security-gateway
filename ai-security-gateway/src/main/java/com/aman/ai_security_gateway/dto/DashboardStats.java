package com.aman.ai_security_gateway.dto;

import java.util.Map;

public record DashboardStats(

        long totalRequests,

        long blockedRequests,

        long allowedRequests,

        double averageRiskScore,

        Map<String, Long> threatCounts
) {
}