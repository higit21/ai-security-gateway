package com.aman.ai_security_gateway.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final Client client;

    public AIService(@Value("${gemini.api.key}") String apiKey) {

        this.client = Client.builder()
                .apiKey(apiKey)
                .build();
    }

    public String askGemini(String prompt) {

        System.out.println("Sending To Gemini:");
        System.out.println(prompt);

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-3.1-flash-lite",
                        prompt,
                        null
                );
        return response.text();
    }
}