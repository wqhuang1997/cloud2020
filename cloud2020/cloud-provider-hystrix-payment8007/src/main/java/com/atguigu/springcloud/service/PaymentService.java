package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class PaymentService {

    public String paymentInfoOK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "id:" + id;

    }

    @HystrixCommand(fallbackMethod = "paymentTimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfoTimeout(Integer id) throws InterruptedException {
        //int i = 10/0;
        Thread.sleep(3000);
        return "线程池：" + Thread.currentThread().getName() + "id:" + id;
    }

    public String paymentTimeOutHandler(Integer id) {
        return "fallback线程池：" + Thread.currentThread().getName() + "id:" + id;
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//错误率
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("no < 0");
        }
        return Thread.currentThread().getName() + "\t" + IdUtil.simpleUUID();
    }

    public String paymentCircuitBreakerFallback(Integer id) {
        return "later try";
    }

}
