package com.aman.ai_security_gateway.provider;

import com.aman.ai_security_gateway.service.AIService;
import org.springframework.stereotype.Component;

@Component
public class GeminiProvider implements AIProvider {

    private final AIService aiService;

    public GeminiProvider(AIService aiService) {
        this.aiService = aiService;
    }

    @Override
    public String ask(String prompt) {
        return aiService.askGemini(prompt);
    }

    @Override
    public String getProviderName() {
        return "Gemini";
    }
}