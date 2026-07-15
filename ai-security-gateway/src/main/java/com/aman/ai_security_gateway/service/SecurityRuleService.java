package com.aman.ai_security_gateway.service;

import com.aman.ai_security_gateway.entity.SecurityRuleEntity;
import com.aman.ai_security_gateway.repository.SecurityRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityRuleService {

    private final SecurityRuleRepository repository;

    public SecurityRuleService(
            SecurityRuleRepository repository
    ) {
        this.repository = repository;
    }

    public List<SecurityRuleEntity> getAllRules() {

        return repository.findAll();
    }

    public SecurityRuleEntity getRuleById(Long id) {

        return findRuleOrThrow(id);
    }

    public SecurityRuleEntity createRule(
            SecurityRuleEntity rule
    ) {

        rule.setId(null);

        if (rule.getEnabled() == null) {
            rule.setEnabled(true);
        }

        return repository.save(rule);
    }

    public SecurityRuleEntity updateRule(
            Long id,
            SecurityRuleEntity updatedRule
    ) {

        SecurityRuleEntity existingRule =
                findRuleOrThrow(id);

        existingRule.setType(
                updatedRule.getType()
        );

        existingRule.setPattern(
                updatedRule.getPattern()
        );

        existingRule.setMessage(
                updatedRule.getMessage()
        );

        existingRule.setSeverity(
                updatedRule.getSeverity()
        );

        existingRule.setAction(
                updatedRule.getAction()
        );

        existingRule.setEnabled(
                updatedRule.getEnabled()
        );

        return repository.save(existingRule);
    }

    public void deleteRule(Long id) {

        SecurityRuleEntity rule =
                findRuleOrThrow(id);

        repository.delete(rule);
    }

    public SecurityRuleEntity enableRule(Long id) {

        SecurityRuleEntity rule =
                findRuleOrThrow(id);

        rule.setEnabled(true);

        return repository.save(rule);
    }

    public SecurityRuleEntity disableRule(Long id) {

        SecurityRuleEntity rule =
                findRuleOrThrow(id);

        rule.setEnabled(false);

        return repository.save(rule);
    }

    private SecurityRuleEntity findRuleOrThrow(
            Long id
    ) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Security rule not found with id: "
                                        + id
                        )
                );
    }
}