package com.example.bankingsystem.payment;

import com.example.bankingsystem.payment.adaptee.LocalAPI;
import com.example.bankingsystem.payment.adaptee.PayPalService;
import com.example.bankingsystem.payment.adaptee.StripePayment;
import com.example.bankingsystem.payment.adaptee.VisaDirect;
import com.example.bankingsystem.payment.adapter.LocalProcessorAdapter;
import com.example.bankingsystem.payment.adapter.PayPalAdapter;
import com.example.bankingsystem.payment.adapter.StripeAdapter;
import com.example.bankingsystem.payment.adapter.VisaAdapter;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayFactory {

    private final StripePayment stripePayment = new StripePayment();
    private final PayPalService payPalService = new PayPalService();
    private final VisaDirect visaDirect = new VisaDirect();
    private final LocalAPI localAPI = new LocalAPI();

    public PaymentGateway getGateway(String type) {
        return switch (type.toLowerCase()) {
            case "stripe" -> new StripeAdapter(stripePayment);
            case "paypal" -> new PayPalAdapter(payPalService);
            case "visa" -> new VisaAdapter(visaDirect);
            case "local" -> new LocalProcessorAdapter(localAPI);
            default -> throw new IllegalArgumentException("Unknown payment gateway type: " + type);
        };
    }
}
