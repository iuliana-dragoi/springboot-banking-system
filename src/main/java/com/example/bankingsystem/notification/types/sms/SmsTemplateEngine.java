package com.example.bankingsystem.notification.types.sms;

import com.example.bankingsystem.notification.factory.TemplateEngine;

public class SmsTemplateEngine implements TemplateEngine {

    @Override
    public String renderTemplate(String template, Object context) {
        return "SMS template [" + template + "]";
    }
}
