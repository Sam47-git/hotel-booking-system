package com.sam.project.AirBnb.config;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripConfig {

    public StripConfig(@Value("${stripe.secret.key}") String stripeSecretKey) {
        Stripe.apiKey = stripeSecretKey;
    }
}
