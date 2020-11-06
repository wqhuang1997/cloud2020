package com.atguigu.springcloud;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/hystrix/ok/{id}")
    public String paymentInfoOK(@PathVariable("id") Integer id) {
        return paymentService.paymentInfoOK(id);
    }

    @GetMapping(value = "/hystrix/timeout/{id}")
    public String paymentInfo(@PathVariable("id") Integer id) throws InterruptedException {
        //Thread.sleep(3000);
        return paymentService.paymentInfoTimeout(id);
    }

    @GetMapping(value = "/circuit/{id}")
    public String circuit(@PathVariable("id") Integer id) {
        return paymentService.paymentCircuitBreaker(id);
    }

}
