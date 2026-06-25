package com.aman.ai_security_gateway.security.rules;

import com.aman.ai_security_gateway.security.SecurityRule;
import com.aman.ai_security_gateway.security.SecurityThreat;
import com.aman.ai_security_gateway.security.ThreatSeverity;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class AwsKeyRule
        implements SecurityRule {

    private static final Pattern AWS_PATTERN =
            Pattern.compile(
                    "AKIA[0-9A-Z]{16}"
            );

    @Override
    public SecurityThreat evaluate(
            String prompt
    ) {

        if (AWS_PATTERN
                .matcher(prompt)
                .find()) {

            return new SecurityThreat(
                    "AWS_KEY",
                    "Contains AWS access key",
                    ThreatSeverity.CRITICAL
            );
        }

        return null;
    }
}