package com.aman.ai_security_gateway.security;

public interface SecurityRule {
    SecurityThreat evaluate(String prompt);
}
