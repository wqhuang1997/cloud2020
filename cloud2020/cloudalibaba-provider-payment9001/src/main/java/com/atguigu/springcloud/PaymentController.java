package com.atguigu.springcloud;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    private static HashMap<Integer, Payment> paymentHashMap = new HashMap<>();

    static {
        paymentHashMap.put(1, new Payment(1L, "111111"));
        paymentHashMap.put(2, new Payment(2L, "222222"));
        paymentHashMap.put(3, new Payment(3L, "333333"));

    }
    @GetMapping(value = "/nacos/{id}")
    public String getServerPort(@PathVariable("id") Integer id) {
        System.out.println("in 9001");
        return "nacos, port:" + serverPort + "id is :" + id;
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> payment(@PathVariable("id") Integer id) {
        Payment payment = paymentHashMap.get(id);
        return new CommonResult<>(200, "port: " + serverPort, payment);

    }
}
