package com.example.bankingsystem.notification.types.email;

import com.example.bankingsystem.notification.factory.TemplateEngine;

public class EmailTemplateEngine implements TemplateEngine {


    @Override
    public String renderTemplate(String template, Object context) {
        return "Email template [" + template + "]";
    }
}
