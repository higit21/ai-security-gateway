package com.aman.ai_security_gateway.service;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class RedactionService {


    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+");

    private static final Pattern AWS_KEY_PATTERN =
            Pattern.compile("AKIA[0-9A-Z]{16}");

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("(?i)password\\s*[:=]\\s*\\S+");

    public String redact(String prompt) {

        String sanitized = prompt;

        sanitized = EMAIL_PATTERN
                .matcher(sanitized)
                .replaceAll("[REDACTED_EMAIL]");

        sanitized = AWS_KEY_PATTERN
                .matcher(sanitized)
                .replaceAll("[REDACTED_AWS_KEY]");

        sanitized = PASSWORD_PATTERN
                .matcher(sanitized)
                .replaceAll("[REDACTED_PASSWORD]");

        return sanitized;
    }
}