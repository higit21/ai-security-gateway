package com.aman.ai_security_gateway.security;

public record RuleMatch(
        SecurityThreat threat,
        RuleAction action,
        String pattern
) {
}
