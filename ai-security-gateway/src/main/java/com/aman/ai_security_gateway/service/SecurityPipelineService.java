package com.aman.ai_security_gateway.service;

import com.aman.ai_security_gateway.dto.PipelineResponse;
import com.aman.ai_security_gateway.security.RuleAction;
import com.aman.ai_security_gateway.security.RuleMatch;
import com.aman.ai_security_gateway.security.SecurityThreat;
import com.aman.ai_security_gateway.security.ThreatAssessment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Service
public class SecurityPipelineService {

    private final RiskAssessmentService riskAssessmentService;
    private final AIRouterService aiRouterService;
    private final AuditService auditService;
    private final DynamicRuleScannerService dynamicRuleScannerService;

    public SecurityPipelineService(
            RiskAssessmentService riskAssessmentService,
            AIRouterService aiRouterService,
            AuditService auditService,
            DynamicRuleScannerService dynamicRuleScannerService
    ) {
        this.riskAssessmentService = riskAssessmentService;
        this.aiRouterService = aiRouterService;
        this.auditService = auditService;
        this.dynamicRuleScannerService =
                dynamicRuleScannerService;
    }

    public PipelineResponse process(String prompt) {

        List<RuleMatch> matches =
                dynamicRuleScannerService.scan(prompt);

        List<SecurityThreat> threats =
                matches.stream()
                        .map(RuleMatch::threat)
                        .toList();

        ThreatAssessment assessment =
                riskAssessmentService.assess(threats);

        boolean shouldBlock =
                matches.stream()
                        .anyMatch(match ->
                                match.action()
                                        == RuleAction.BLOCK
                        );

        if (shouldBlock) {

            String status = "BLOCKED";

            auditService.saveLog(
                    prompt,
                    null,
                    status,
                    assessment.riskScore(),
                    true,
                    threats.stream()
                            .map(SecurityThreat::type)
                            .toList()
            );

            return new PipelineResponse(
                    true,
                    assessment.riskScore(),
                    status,
                    threats,
                    null
            );
        }

        String sanitizedPrompt =
                applyRedactionRules(
                        prompt,
                        matches
                );

        boolean redacted =
                !sanitizedPrompt.equals(prompt);

        String status =
                redacted
                        ? "ALLOWED_AFTER_REDACTION"
                        : "ALLOWED";

        String response =
                aiRouterService.ask(
                        sanitizedPrompt
                );

        auditService.saveLog(
                prompt,
                response,
                status,
                assessment.riskScore(),
                false,
                threats.stream()
                        .map(SecurityThreat::type)
                        .toList()
        );

        return new PipelineResponse(
                false,
                assessment.riskScore(),
                status,
                threats,
                response
        );
    }

    private String applyRedactionRules(
            String prompt,
            List<RuleMatch> matches
    ) {

        String sanitizedPrompt = prompt;

        for (RuleMatch match : matches) {

            if (match.action()
                    != RuleAction.REDACT) {
                continue;
            }

            try {

                sanitizedPrompt =
                        Pattern.compile(
                                        match.pattern(),
                                        Pattern.CASE_INSENSITIVE
                                )
                                .matcher(sanitizedPrompt)
                                .replaceAll("[REDACTED]");

            } catch (PatternSyntaxException exception) {

                System.err.println(
                        "Unable to apply redaction for pattern: "
                                + match.pattern()
                                + ". Error: "
                                + exception.getMessage()
                );
            }
        }

        return sanitizedPrompt;
    }
}