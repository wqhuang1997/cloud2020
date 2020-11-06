package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.FallbackHandler;
import com.atguigu.springcloud.service.IOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private static final String URL = "http://nacos-payment-provider";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IOrder order;

    @GetMapping(value = "/hashGet/{id}")
    //@SentinelResource(value = "fallback", fallback = "handlerFallback")// fallback只负责业务异常
    //@SentinelResource(value = "fallback", blockHandler = "blockHandler") //blockHandler只负责sentinel控制台配置违规
    @SentinelResource(value = "fallback", fallback = "handlerFallback",
            blockHandler = "blockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class}) //blockHandler只负责sentinel控制台配置违规
    public CommonResult hashGet(@PathVariable("id") Integer id) {
        CommonResult commonResult =  restTemplate.getForObject(URL + "/payment/paymentSQL/" + id, CommonResult.class);

        if (id.equals(4)) {
            throw new IllegalArgumentException("非法参数异常");
        }
        if (commonResult == null) {
            throw new NullPointerException("空数据异常");
        }
        return commonResult;
    }

    @GetMapping(value = "/fegin/{id}")
    //@SentinelResource(value = "fallback", fallback = "handlerFallback")// fallback只负责业务异常
    //@SentinelResource(value = "fallback", blockHandler = "blockHandler") //blockHandler只负责sentinel控制台配置违规
    @SentinelResource(value = "fallback",
            blockHandler = "blockHandler",
            fallbackClass = FallbackHandler.class,
            fallback = "FeginHandler")
            //exceptionsToIgnore = {IllegalArgumentException.class}) //blockHandler只负责sentinel控制台配置违规
    public CommonResult fegin(@PathVariable("id") Integer id) {

        //CommonResult commonResult =  restTemplate.getForObject(URL + "/payment/paymentSQL/" + id, CommonResult.class);

        CommonResult commonResult = order.getPayment(id);

        if (id.equals(4)) {
            throw new IllegalArgumentException("非法参数异常");
        }
        if (commonResult == null) {
            throw new NullPointerException("空数据异常");
        }
        return commonResult;
    }

    public CommonResult handlerFallback(Integer id, Throwable e) {
        return new CommonResult(404, "兜底异常处理---------", e.getMessage());
    }


    public CommonResult blockHandler(Integer id, BlockException e) {
        return new CommonResult(444, "block异常处理-仅限控制台--------", e.getMessage());
    }
}
