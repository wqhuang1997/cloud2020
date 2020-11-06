//package com.atguigu.springcloud.service;
//
//import com.atguigu.springcloud.entities.CommonResult;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@FeignClient(value = "nacos-payment-provider", fallback = Handler.class)
//public interface Payment {
//
//    @GetMapping(value = "/payment/paymentSQL/{id}")
//    CommonResult get(@PathVariable("id") Integer id);
//}
