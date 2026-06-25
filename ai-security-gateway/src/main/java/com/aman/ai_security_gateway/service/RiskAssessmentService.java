package com.aman.ai_security_gateway.service;

import com.aman.ai_security_gateway.security.SecurityThreat;
import com.aman.ai_security_gateway.security.ThreatAssessment;
import com.aman.ai_security_gateway.security.ThreatSeverity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskAssessmentService {

    public ThreatAssessment assess(
            List<SecurityThreat> threats
    ) {

        int score = 0;

        for (SecurityThreat threat : threats) {

            score += switch (
                    threat.severity()
                    ) {

                case LOW -> 10;
                case MEDIUM -> 40;
                case HIGH -> 70;
                case CRITICAL -> 100;
            };
        }

        score = Math.min(score, 100);

        boolean blocked =
                threats.stream()
                        .anyMatch(
                                threat ->
                                        threat.severity()
                                                == ThreatSeverity.CRITICAL
                        );

        return new ThreatAssessment(
                score,
                blocked,
                threats
        );
    }
}