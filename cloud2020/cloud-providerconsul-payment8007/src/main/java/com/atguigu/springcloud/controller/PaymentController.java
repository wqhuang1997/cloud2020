package com.atguigu.springcloud.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/payment")
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/consul")
    public String paymentZK() {
        return "consul port:" + serverPort;
    }
}
