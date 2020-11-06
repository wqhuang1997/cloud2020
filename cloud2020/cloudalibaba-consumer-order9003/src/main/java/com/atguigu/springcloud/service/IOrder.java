package com.atguigu.springcloud.service;


import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "${service.name}", fallback = Handler.class)
public interface IOrder {


    @GetMapping(value = "/payment/nacos/{id}")
    String get(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/paymentSQL/{id}")
    CommonResult getPayment(@PathVariable("id") Integer id);
}
