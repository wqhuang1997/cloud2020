package com.atguigu.springcloud;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

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

    @GetMapping(value = "/lb")
    public String getServerPort() {
        return serverPort;
    }

}
