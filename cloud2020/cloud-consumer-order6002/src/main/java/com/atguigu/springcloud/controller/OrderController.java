package com.atguigu.springcloud.controller;



import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.MyLB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/consumer/payment")
public class OrderController {

    public static final String URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private MyLB myLB;

    @PostMapping(value = "/create")
    public CommonResult create(Payment payment) {
        return restTemplate.postForObject(URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping(value = "/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping(value = "/getEntity/{id}")
    public CommonResult getEntityPayment(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(URL + "/payment/get/" + id, CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()) {
            return forEntity.getBody();
        }
        return new CommonResult(520, "no find payment");
    }


    @GetMapping(value = "/get/lb/{id}")
    public CommonResult getLb(@PathVariable("id") Long id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (CollectionUtils.isEmpty(instances)) {
            return null;
        }
        ServiceInstance instances1 = myLB.instances(instances);
        URI uri = instances1.getUri();
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(uri + "/payment/get/" + id, CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()) {
            return forEntity.getBody();
        }
        return new CommonResult(520, "no find payment");

    }

}
