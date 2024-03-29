package com.example.paymentservice.config;


import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorPayConfig {

    @Value("${razorpay.key.id}") // this will be fetched from app.properties
    private String razorPayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorPaySecret;

    @Bean
    public  RazorpayClient getRazorPayClient() throws RazorpayException{
        return new RazorpayClient(razorPayKeyId,razorPaySecret);
    }
}
