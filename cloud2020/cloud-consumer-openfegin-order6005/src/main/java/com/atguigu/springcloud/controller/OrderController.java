package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/consumer")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id) {
        return orderService.getPaymentById(id);
    }

    @GetMapping(value = "/timeout")
    public String timeOut() {
        return orderService.timeOut();
    }}
