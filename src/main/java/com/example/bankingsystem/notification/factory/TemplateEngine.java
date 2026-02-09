package com.example.bankingsystem.notification.factory;

public interface TemplateEngine {
    String renderTemplate(String template, Object context);
}
