package com.aman.ai_security_gateway.security.rules;

import com.aman.ai_security_gateway.security.SecurityRule;
import com.aman.ai_security_gateway.security.SecurityThreat;
import com.aman.ai_security_gateway.security.ThreatSeverity;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PasswordRule implements SecurityRule {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile(
                    "(?i)password\\s*[:=]\\s*\\S+"
            );

    @Override
    public SecurityThreat evaluate(String prompt) {

        if (PASSWORD_PATTERN.matcher(prompt).find()) {

            return new SecurityThreat(
                    "PASSWORD",
                    "Contains password",
                    ThreatSeverity.HIGH
            );
        }

        return null;
    }
}