package com.aman.ai_security_gateway.repository;

import com.aman.ai_security_gateway.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository
        extends JpaRepository<AuditLog, Long> {
}
