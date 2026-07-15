package com.aman.ai_security_gateway.controller;

import com.aman.ai_security_gateway.entity.SecurityRuleEntity;
import com.aman.ai_security_gateway.service.SecurityRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class SecurityRuleController {

    private final SecurityRuleService securityRuleService;

    public SecurityRuleController(
            SecurityRuleService securityRuleService
    ) {
        this.securityRuleService =
                securityRuleService;
    }

    @GetMapping
    public List<SecurityRuleEntity> getAllRules() {
        return securityRuleService.getAllRules();
    }

    @PostMapping
    public SecurityRuleEntity createRule(
            @RequestBody SecurityRuleEntity rule
    ) {
        return securityRuleService.createRule(rule);
    }

    @PutMapping("/{id}")
    public SecurityRuleEntity updateRule(
            @PathVariable Long id,
            @RequestBody SecurityRuleEntity rule
    ) {
        return securityRuleService.updateRule(
                id,
                rule
        );
    }

    @DeleteMapping("/{id}")
    public void deleteRule(
            @PathVariable Long id
    ) {
        securityRuleService.deleteRule(id);
    }

    @PatchMapping("/{id}/enable")
    public SecurityRuleEntity enableRule(
            @PathVariable Long id
    ) {
        return securityRuleService.enableRule(id);
    }

    @PatchMapping("/{id}/disable")
    public SecurityRuleEntity disableRule(
            @PathVariable Long id
    ) {
        return securityRuleService.disableRule(id);
    }
}