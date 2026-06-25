package com.aman.ai_security_gateway.security.rules;

import com.aman.ai_security_gateway.security.SecurityRule;
import com.aman.ai_security_gateway.security.SecurityThreat;
import com.aman.ai_security_gateway.security.ThreatSeverity;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PromptInjectionRule
        implements SecurityRule {

    private static final Pattern PROMPT_INJECTION_PATTERN =
            Pattern.compile(
                    "(?i)(ignore previous instructions|" +
                            "developer mode|" +
                            "system prompt|" +
                            "bypass security|" +
                            "act as admin|" +
                            "disable safety)"
            );

    @Override
    public SecurityThreat evaluate(String prompt) {

        if (PROMPT_INJECTION_PATTERN
                .matcher(prompt)
                .find()) {

            return new SecurityThreat(
                    "PROMPT_INJECTION",
                    "Possible prompt injection attempt",
                    ThreatSeverity.HIGH
            );
        }

        return null;
    }
}