package com.aman.ai_security_gateway.security;

import java.util.List;

public record ThreatAssessment(
        int riskScore,
        boolean blocked,
        List<SecurityThreat> threats
) {
}