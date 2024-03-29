package com.example.paymentservice.controller;

import com.example.paymentservice.dto.InitiatePaymentRequestDTO;
import com.example.paymentservice.dto.PaymentResponse;
import com.example.paymentservice.service.PaymentService;
import com.example.paymentservice.service.strategy.PaymentGatewaySelectionStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private PaymentService razorPaymentService;
    private PaymentService stripePaymentService;

    private PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy;

    public PaymentController(@Qualifier("razorpay") PaymentService razorPaymentService,
                             @Qualifier("stripe") PaymentService stripePaymentService,
            PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy) {
        this.razorPaymentService = razorPaymentService;
        this.stripePaymentService = stripePaymentService;
        this.paymentGatewaySelectionStrategy=paymentGatewaySelectionStrategy;

    }

    @RequestMapping("/payment")
    public String doPayment(@RequestBody InitiatePaymentRequestDTO initiatePaymentRequestDTO) throws Exception {
        int paymentGatewayOption = choosePaymentGateway();
        switch (paymentGatewayOption) {
            case 0:
                return razorPaymentService.doPayment(initiatePaymentRequestDTO.getEmail(), initiatePaymentRequestDTO.getPhonenumber(), initiatePaymentRequestDTO.getAmount(), initiatePaymentRequestDTO.getOrderId());
            case 1:
                return stripePaymentService.doPayment(initiatePaymentRequestDTO.getEmail(), initiatePaymentRequestDTO.getPhonenumber(), initiatePaymentRequestDTO.getAmount(), initiatePaymentRequestDTO.getOrderId());
        }
        return null;
    }

    private int choosePaymentGateway() {
        return 0;
    }
}
