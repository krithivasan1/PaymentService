package com.example.paymentservice.service;

import com.example.paymentservice.dto.PaymentResponse;
import org.springframework.stereotype.Service;


@Service("stripe")
public class StripePaymentServiceImpl implements PaymentService{
    @Override
    public PaymentResponse doPayment(String email, String phone, Long amount, String orderId) {
        return null;
    }
}
