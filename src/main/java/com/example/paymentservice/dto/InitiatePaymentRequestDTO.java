package com.example.paymentservice.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentRequestDTO {
    private String email;
    private String phonenumber;
    private long amount;
    private String orderId;
}
