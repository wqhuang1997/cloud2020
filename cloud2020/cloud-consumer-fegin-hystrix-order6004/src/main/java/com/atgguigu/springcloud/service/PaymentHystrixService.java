package com.atgguigu.springcloud.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixService implements IPaymentHystrixService{
    @Override
    public String paymentOK(Integer id) {
        return "payment hystrix service for fallback";
    }

    @Override
    public String paymentTimeout(Integer id) {
        return "payment hystrix service for fallback";
    }
}
