package com.aman.ai_security_gateway.security;

public record SecurityThreat(String type,String message, ThreatSeverity severity) {
}
