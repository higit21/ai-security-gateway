package com.aman.ai_security_gateway.service;

import com.aman.ai_security_gateway.dto.PipelineResponse;
import com.aman.ai_security_gateway.security.SecurityThreat;
import com.aman.ai_security_gateway.security.ThreatAssessment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityPipelineService {

    private final SecurityScannerService scannerService;
    private final RiskAssessmentService riskAssessmentService;
    private final RedactionService redactionService;
    private final AIRouterService aiRouterService;
    private final AuditService auditService;

    public SecurityPipelineService(
            SecurityScannerService scannerService,
            RiskAssessmentService riskAssessmentService,
            RedactionService redactionService,
            AIRouterService aiRouterService,
            AuditService auditService
    ) {
        this.scannerService = scannerService;
        this.riskAssessmentService = riskAssessmentService;
        this.redactionService = redactionService;
        this.aiRouterService = aiRouterService;
        this.auditService = auditService;
    }

    public PipelineResponse process(String prompt) {

        List<SecurityThreat> threats =
                scannerService.scan(prompt);

        ThreatAssessment assessment =
                riskAssessmentService.assess(threats);

        if (assessment.blocked()) {

            String status = "BLOCKED";

            auditService.saveLog(
                    prompt,
                    null,
                    status,
                    assessment.riskScore(),
                    true,
                    assessment.threats()
                            .stream()
                            .map(SecurityThreat::type)
                            .toList()
            );

            return new PipelineResponse(
                    true,
                    assessment.riskScore(),
                    status,
                    assessment.threats(),
                    null
            );
        }

        String sanitizedPrompt =
                redactionService.redact(prompt);

        boolean redacted =
                !sanitizedPrompt.equals(prompt);

        String status =
                redacted
                        ? "ALLOWED_AFTER_REDACTION"
                        : "ALLOWED";

        String response =
                aiRouterService.ask(sanitizedPrompt);

        auditService.saveLog(
                prompt,
                response,
                status,
                assessment.riskScore(),
                false,
                assessment.threats()
                        .stream()
                        .map(SecurityThreat::type)
                        .toList()
        );

        return new PipelineResponse(
                false,
                assessment.riskScore(),
                status,
                assessment.threats(),
                response
        );
    }
}