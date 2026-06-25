package com.aman.ai_security_gateway.security.rules;

import com.aman.ai_security_gateway.security.SecurityRule;
import com.aman.ai_security_gateway.security.SecurityThreat;
import com.aman.ai_security_gateway.security.ThreatSeverity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class SqlInjectionRule implements SecurityRule {

    private static final List<Pattern> SQL_PATTERNS =
            List.of(
                    Pattern.compile("(?i)\\bor\\b\\s+1\\s*=\\s*1"),
                    Pattern.compile("(?i)\\band\\b\\s+1\\s*=\\s*1"),
                    Pattern.compile("(?i)union\\s+select"),
                    Pattern.compile("(?i)select\\s+.*\\s+from"),
                    Pattern.compile("(?i)drop\\s+table"),
                    Pattern.compile("(?i)delete\\s+from"),
                    Pattern.compile("(?i)insert\\s+into"),
                    Pattern.compile("(?i)update\\s+.*\\s+set"),
                    Pattern.compile("(?i)information_schema"),
                    Pattern.compile("(?i)xp_cmdshell"),
                    Pattern.compile("(?i)--"),
                    Pattern.compile("(?i)/\\*.*\\*/"),
                    Pattern.compile("(?i);\\s*shutdown"),
                    Pattern.compile("(?i);\\s*drop"),
                    Pattern.compile("(?i);\\s*delete"),
                    Pattern.compile("(?i)'\\s*or\\s*'1'\\s*=\\s*'1"),
                    Pattern.compile("(?i)\"\\s*or\\s*\"1\"\\s*=\\s*\"1")
            );

    @Override
    public SecurityThreat evaluate(String prompt) {

        for (Pattern pattern : SQL_PATTERNS) {

            if (pattern.matcher(prompt).find()) {

                return new SecurityThreat(
                        "SQL_INJECTION",
                        "Possible SQL injection detected",
                        ThreatSeverity.CRITICAL
                );
            }
        }

        return null;
    }
}