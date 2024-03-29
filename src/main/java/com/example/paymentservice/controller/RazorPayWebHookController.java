package com.example.paymentservice.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/razorpayWebHook")
public class RazorPayWebHookController {

    @PostMapping("/")
    public  ResponseEntity acceptWebHookRequest(){
        //redirect to UI where form for entering the credit card information will be entered
        // Just to test
        System.out.println("WEb hook requested from Payment Gateway");
        return null;
    }

}
