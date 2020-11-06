package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeoutException;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create/order")
    public CommonResult create(@RequestBody Order order) throws Exception {
        return orderService.create(order);
       // return new CommonResult(200, "create order success");
    }

//    @PostMapping("/get/order")
//    public CommonResult get(@RequestBody Order order) throws TimeoutException {
//        orderService.get();
//        return new CommonResult(200, "create order success");
//    }

}
