package com.atguigu.springcloud;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Value("${spring.application.name}")
    private String springApplicationName;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        if (result > 0) {
            return new CommonResult(200, "success", result);
        } else {
            return new CommonResult(500, "fail", null);
        }

    }

    @GetMapping(value = "/get/{id}")
    public CommonResult getById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult(200, "success, port: " + serverPort, payment);
        } else {
            return new CommonResult(200, "success, port: " + serverPort, "no exist");
        }

    }

    @GetMapping(value = "/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service:{}", service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances(springApplicationName);
        for (ServiceInstance instance : instances) {
            log.info("host={}, instanceId={}, serviceId={}", instance.getHost(), instance.getInstanceId(), instance.getServiceId());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/zk")
    public String paymentZK() {
        return "zk port:" + serverPort;
    }
}
