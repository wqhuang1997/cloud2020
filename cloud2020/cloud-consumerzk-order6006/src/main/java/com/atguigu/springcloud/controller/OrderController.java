package com.atguigu.springcloud.controller;



import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer/payment")
public class OrderController {

    public static final String URL = "http://cloud-payment-service";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "/create")
    public CommonResult create(Payment payment) {
        return restTemplate.postForObject(URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping(value = "/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping(value = "/zk")
    public String paymentInfo() {
        return restTemplate.getForObject(URL + "/payment/zk", String.class);
    }

}
