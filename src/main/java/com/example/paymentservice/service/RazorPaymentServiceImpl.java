package com.example.paymentservice.service;

import com.example.paymentservice.dto.PaymentResponse;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import com.razorpay.RazorpayClient;

@Service("razorpay")
public class RazorPaymentServiceImpl implements PaymentService{
    @Autowired
    RazorpayClient razorpayClient;
    @Override
    public String doPayment(String email, String phone, Long amount,String orderId) throws RazorpayException {
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("receipt",orderId);

        JSONObject customer = new JSONObject();
        customer.put("email",email);
        customer.put("phone",phone);
        paymentLinkRequest.put("Customer",customer);

        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);

        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("callback_url","https://domain.com/razorpayWebHook");
        paymentLinkRequest.put("callback_method","get");
        PaymentLink paymentResponse = razorpayClient.paymentLink.create(paymentLinkRequest);
        return paymentResponse.toString();
    }
}
