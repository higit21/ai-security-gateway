package com.aman.ai_security_gateway.repository;

import com.aman.ai_security_gateway.entity.SecurityRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SecurityRuleRepository extends JpaRepository<SecurityRuleEntity,Long> {
    List<SecurityRuleEntity> findByEnabled(Boolean enabled);
}
