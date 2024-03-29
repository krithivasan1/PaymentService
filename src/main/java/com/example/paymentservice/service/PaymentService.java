package com.example.paymentservice.service;

import com.example.paymentservice.dto.PaymentResponse;


public interface PaymentService {
    String  doPayment(String email, String phone, Long amount, String orderId) throws Exception;
}
