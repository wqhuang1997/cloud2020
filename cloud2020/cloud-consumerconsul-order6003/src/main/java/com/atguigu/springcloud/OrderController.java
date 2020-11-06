package com.atguigu.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/payment")
public class OrderController {
    public static final String URL = "http://consul-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/consul")
    public String paymentInfo() {
        return restTemplate.getForObject(URL + "/payment/consul", String.class);
    }
}
