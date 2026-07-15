package com.aman.ai_security_gateway.service;

import com.aman.ai_security_gateway.entity.SecurityRuleEntity;
import com.aman.ai_security_gateway.repository.SecurityRuleRepository;
import com.aman.ai_security_gateway.security.RuleMatch;
import com.aman.ai_security_gateway.security.SecurityThreat;
import com.aman.ai_security_gateway.security.ThreatSeverity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Service
public class DynamicRuleScannerService {

    private final SecurityRuleRepository ruleRepository;

    public DynamicRuleScannerService(
            SecurityRuleRepository ruleRepository
    ) {
        this.ruleRepository = ruleRepository;
    }

    public List<RuleMatch> scan(String prompt) {

        List<RuleMatch> matches =
                new ArrayList<>();

        List<SecurityRuleEntity> rules =
                ruleRepository.findByEnabled(true);

        for (SecurityRuleEntity rule : rules) {

            try {

                Pattern pattern =
                        Pattern.compile(
                                rule.getPattern(),
                                Pattern.CASE_INSENSITIVE
                        );

                boolean matched =
                        pattern.matcher(prompt).find();

                if (matched) {

                    SecurityThreat threat =
                            new SecurityThreat(
                                    rule.getType(),
                                    rule.getMessage(),
                                    ThreatSeverity.valueOf(
                                            rule.getSeverity()
                                                    .toUpperCase()
                                    )
                            );

                    matches.add(
                            new RuleMatch(
                                    threat,
                                    rule.getAction(),
                                    rule.getPattern()
                            )
                    );
                }

            } catch (PatternSyntaxException exception) {

                System.err.println(
                        "Invalid regex for rule "
                                + rule.getType()
                                + ": "
                                + exception.getMessage()
                );
            }
        }

        return matches;
    }
}