package com.aman.ai_security_gateway.provider;

public interface AIProvider {

    String ask(String prompt);

    String getProviderName();
}