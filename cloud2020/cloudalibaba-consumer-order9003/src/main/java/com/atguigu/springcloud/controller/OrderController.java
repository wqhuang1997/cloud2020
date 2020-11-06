package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.IOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class OrderController {

    @Autowired
    private IOrder order;

    @GetMapping(value = "/nacos/{id}")
    public String get(@PathVariable("id") Integer id) {
        return order.get(id);
    }
}
