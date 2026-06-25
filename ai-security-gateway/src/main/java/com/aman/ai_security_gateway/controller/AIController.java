package com.aman.ai_security_gateway.controller;

import com.aman.ai_security_gateway.dto.AskRequest;
import com.aman.ai_security_gateway.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AIController {

    private final SecurityPipelineService
            securityPipelineService;

    private final AuditService auditService;

    public AIController(
            SecurityPipelineService securityPipelineService,
            AuditService auditService
    ) {

        this.securityPipelineService =
                securityPipelineService;

        this.auditService = auditService;
    }

    @PostMapping("/ask")
    public Object ask(
            @RequestBody AskRequest request
    ) {

        return securityPipelineService
                .process(
                        request.prompt()
                );
    }

    @GetMapping("/logs")
    public Object logs() {

        return auditService.getAllLogs();
    }

    @GetMapping("/dashboard")
    public Object dashboard() {

        return auditService.getDashboardStats();
    }
}