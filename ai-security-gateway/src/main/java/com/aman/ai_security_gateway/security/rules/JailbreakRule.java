package com.aman.ai_security_gateway.security.rules;

import com.aman.ai_security_gateway.security.SecurityRule;
import com.aman.ai_security_gateway.security.SecurityThreat;
import com.aman.ai_security_gateway.security.ThreatSeverity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class JailbreakRule implements SecurityRule {

    private static final List<Pattern> JAILBREAK_PATTERNS =
            List.of(
                    Pattern.compile("(?i)ignore\\s+(all\\s+)?previous\\s+instructions"),
                    Pattern.compile("(?i)bypass\\s+safety"),
                    Pattern.compile("(?i)developer\\s+mode"),
                    Pattern.compile("(?i)dan\\s+mode"),
                    Pattern.compile("(?i)act\\s+as\\s+(an\\s+)?unrestricted\\s+ai"),
                    Pattern.compile("(?i)you\\s+are\\s+now\\s+dan"),
                    Pattern.compile("(?i)reveal\\s+(the\\s+)?system\\s+prompt"),
                    Pattern.compile("(?i)pretend\\s+you\\s+are"),
                    Pattern.compile("(?i)do\\s+anything\\s+now"),
                    Pattern.compile("(?i)jailbreak"),
                    Pattern.compile("(?i)disable\\s+safety"),
                    Pattern.compile("(?i)override\\s+rules")
            );

    @Override
    public SecurityThreat evaluate(String prompt) {

        for (Pattern pattern : JAILBREAK_PATTERNS) {

            if (pattern.matcher(prompt).find()) {

                return new SecurityThreat(
                        "JAILBREAK",
                        "Jailbreak attempt detected",
                        ThreatSeverity.CRITICAL
                );
            }
        }

        return null;
    }
}