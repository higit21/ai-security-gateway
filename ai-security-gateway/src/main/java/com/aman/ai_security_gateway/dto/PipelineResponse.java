package com.aman.ai_security_gateway.dto;

import com.aman.ai_security_gateway.security.SecurityThreat;

import java.util.List;

public record PipelineResponse(

        boolean blocked,

        int riskScore,

        String status,

        List<SecurityThreat> issues,

        String response
) {
}