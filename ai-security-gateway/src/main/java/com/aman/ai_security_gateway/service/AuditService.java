package com.aman.ai_security_gateway.service;

import com.aman.ai_security_gateway.dto.DashboardStats;
import com.aman.ai_security_gateway.entity.AuditLog;
import com.aman.ai_security_gateway.repository.AuditLogRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditService {

    private final AuditLogRepository repository;

    public AuditService(AuditLogRepository repository) {
        this.repository = repository;
    }

    public void saveLog(
            String prompt,
            String response,
            String status,
            Integer riskScore,
            Boolean blocked,
            List<String> issues
    ) {

        AuditLog log = new AuditLog();

        log.setPrompt(prompt);
        log.setResponse(response);
        log.setStatus(status);
        log.setRiskScore(riskScore);
        log.setBlocked(blocked);
        log.setIssues(String.join(",", issues));
        log.setCreatedAt(LocalDateTime.now());

        repository.save(log);
    }

    public List<AuditLog> getAllLogs() {
        return repository.findAll();
    }
    public DashboardStats getDashboardStats() {

        List<AuditLog> logs = repository.findAll();

        long totalRequests = logs.size();

        long blockedRequests =
                logs.stream()
                        .filter(AuditLog::getBlocked)
                        .count();

        long allowedRequests =
                totalRequests - blockedRequests;

        double averageRiskScore =
                logs.stream()
                        .mapToInt(AuditLog::getRiskScore)
                        .average()
                        .orElse(0);

        Map<String, Long> threatCounts =
                new HashMap<>();

        for (AuditLog log : logs) {

            if (log.getIssues() == null ||
                    log.getIssues().isBlank()) {
                continue;
            }

            String[] threats =
                    log.getIssues().split(",");

            for (String threat : threats) {

                String key = threat.trim();

                if (key.isBlank()) {
                    continue;
                }

                threatCounts.put(
                        key,
                        threatCounts.getOrDefault(key, 0L) + 1
                );
            }
        }

        return new DashboardStats(
                totalRequests,
                blockedRequests,
                allowedRequests,
                averageRiskScore,
                threatCounts
        );
    }
}