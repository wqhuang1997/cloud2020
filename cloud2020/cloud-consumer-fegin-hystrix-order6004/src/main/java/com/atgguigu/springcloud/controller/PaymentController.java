package com.atgguigu.springcloud.controller;

import com.atgguigu.springcloud.service.IPaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payment")
@DefaultProperties(defaultFallback = "gloFallbackMethod")
public class PaymentController {

    @Autowired
    private IPaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/hystrix/ok/{id}")
    public String getOk(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentOK(id);
   }

    @GetMapping(value = "/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "fallbackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
//    })
    @HystrixCommand
    public String getTimeout(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentTimeout(id);
    }

    public String fallbackMethod(Integer id) {
        return "调用失败";
    }

    public String gloFallbackMethod() {
        return "全局调用失败";
    }

}
