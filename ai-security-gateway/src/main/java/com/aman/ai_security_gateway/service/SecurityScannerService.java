package com.aman.ai_security_gateway.service;

import com.aman.ai_security_gateway.security.SecurityRule;
import com.aman.ai_security_gateway.security.SecurityThreat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityScannerService {

    private final List<SecurityRule> rules;

    public SecurityScannerService(
            List<SecurityRule> rules
    ) {
        this.rules = rules;
    }

    public List<SecurityThreat> scan(
            String prompt
    ) {

        List<SecurityThreat> threats =
                new ArrayList<>();

        for (SecurityRule rule : rules) {

            SecurityThreat threat =
                    rule.evaluate(prompt);

            if (threat != null) {
                threats.add(threat);
            }
        }

        return threats;
    }
}