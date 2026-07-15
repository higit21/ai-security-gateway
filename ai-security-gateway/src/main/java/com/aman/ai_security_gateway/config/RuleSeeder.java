package com.aman.ai_security_gateway.config;

import com.aman.ai_security_gateway.entity.SecurityRuleEntity;
import com.aman.ai_security_gateway.repository.SecurityRuleRepository;
import com.aman.ai_security_gateway.security.RuleAction;
import org.apache.tomcat.util.digester.Rule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RuleSeeder implements CommandLineRunner {

    private final SecurityRuleRepository repository;

    public RuleSeeder(SecurityRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {

        if (repository.count() > 0) {
            return;
        }

        createRule(
                "EMAIL",
                "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}",
                "Contains email address",
                "LOW",
                RuleAction.REDACT
        );

        createRule(
                "PASSWORD",
                "(password|pwd|passcode)\\s*[:=]\\s*\\S+",
                "Contains password",
                "HIGH",
                RuleAction.REDACT
        );

        createRule(
                "AWS_KEY",
                "AKIA[0-9A-Z]{16}",
                "Contains AWS access key",
                "CRITICAL",
                RuleAction.BLOCK
        );

        createRule(
                "SQL_INJECTION",
                "(drop\\s+table|select\\s+.*from|delete\\s+from|insert\\s+into|update\\s+.*set)",
                "Possible SQL injection detected",
                "CRITICAL",
                RuleAction.BLOCK
        );

        createRule(
                "JAILBREAK",
                "(ignore\\s+(all\\s+)?previous\\s+instructions|reveal\\s+system\\s+prompt|act\\s+as\\s+dan)",
                "Possible jailbreak attempt detected",
                "CRITICAL",
                RuleAction.BLOCK
        );

        createRule(
                "PROMPT_INJECTION",
                "(system\\s+prompt|developer\\s+message|hidden\\s+instructions)",
                "Possible prompt injection attempt",
                "HIGH",
                RuleAction.BLOCK
        );
    }

    private void createRule(
            String type,
            String pattern,
            String message,
            String severity,
            RuleAction action
    ) {

        SecurityRuleEntity rule =
                new SecurityRuleEntity();

        rule.setType(type);
        rule.setPattern(pattern);
        rule.setMessage(message);
        rule.setSeverity(severity);
        rule.setEnabled(true);
        rule.setAction(action);

        repository.save(rule);
    }
}