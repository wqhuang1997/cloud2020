package com.atguigu.springcloud.handler;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class timeOutHandler implements AccountService {

    @Override
    public CommonResult decrease(Long userId, BigDecimal money) {
        return new CommonResult(500, "调用失败");
    }

    @Override
    public CommonResult timeout() {
        return new CommonResult(500, "调用失败");

    }
}
