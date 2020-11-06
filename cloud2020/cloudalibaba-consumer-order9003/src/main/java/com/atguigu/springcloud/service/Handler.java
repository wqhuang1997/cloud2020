package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class Handler implements IOrder{
    @Override
    public String get(Integer id) {
        return "服务降级";
    }

    @Override
    public CommonResult getPayment(Integer id) {
        return new CommonResult(3333, "服务降级返回");
    }
}
