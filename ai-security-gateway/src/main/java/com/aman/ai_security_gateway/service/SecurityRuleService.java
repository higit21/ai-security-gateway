package com.aman.ai_security_gateway.service;

import com.aman.ai_security_gateway.entity.SecurityRuleEntity;
import com.aman.ai_security_gateway.repository.SecurityRuleRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable("enabledRules")
    public List<SecurityRuleEntity> getEnabledRules() {
        System.out.println("Fetching enabled rules from DB...");
        return repository.findByEnabled(true);
    }

    public List<SecurityRuleEntity> getAllRules() {

        return repository.findAll();
    }

    public SecurityRuleEntity getRuleById(Long id) {

        return findRuleOrThrow(id);
    }
    @CacheEvict(
            value = "enabledRules",
            allEntries = true
    )
    public SecurityRuleEntity createRule(
            SecurityRuleEntity rule
    ) {

        rule.setId(null);

        if (rule.getEnabled() == null) {
            rule.setEnabled(true);
        }

        return repository.save(rule);
    }
    @CacheEvict(
            value = "enabledRules",
            allEntries = true
    )
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
    @CacheEvict(
            value = "enabledRules",
            allEntries = true
    )
    public void deleteRule(Long id) {

        SecurityRuleEntity rule =
                findRuleOrThrow(id);

        repository.delete(rule);
    }
    @CacheEvict(
            value = "enabledRules",
            allEntries = true
    )
    public SecurityRuleEntity enableRule(Long id) {

        SecurityRuleEntity rule =
                findRuleOrThrow(id);

        rule.setEnabled(true);

        return repository.save(rule);
    }
    @CacheEvict(
            value = "enabledRules",
            allEntries = true
    )
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