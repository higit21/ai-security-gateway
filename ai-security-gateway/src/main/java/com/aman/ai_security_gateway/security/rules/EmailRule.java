package com.aman.ai_security_gateway.security.rules;

import com.aman.ai_security_gateway.security.SecurityRule;
import com.aman.ai_security_gateway.security.SecurityThreat;
import com.aman.ai_security_gateway.security.ThreatSeverity;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailRule
        implements SecurityRule {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile(
                    "[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+"
            );

    @Override
    public SecurityThreat evaluate(
            String prompt
    ) {

        if (EMAIL_PATTERN
                .matcher(prompt)
                .find()) {

            return new SecurityThreat(
                    "EMAIL",
                    "Contains email address",
                    ThreatSeverity.LOW
            );
        }

        return null;
    }
}