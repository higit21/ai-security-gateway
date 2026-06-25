package com.aman.ai_security_gateway.service;

import com.aman.ai_security_gateway.provider.AIProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIRouterService {

    private final List<AIProvider> providers;

    public AIRouterService(List<AIProvider> providers) {
        this.providers = providers;
    }

    public String ask(String prompt) {

        AIProvider provider = selectProvider(prompt);
        System.out.println("Selected Provider: "
                + provider.getProviderName());
        return provider.ask(prompt);
    }

    private AIProvider selectProvider(String prompt) {

        return providers.stream()
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("No AI Provider Found"));
    }
}