package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;

import java.math.BigDecimal;


public interface AccountService {

    CommonResult decrease(Long userId, BigDecimal money) throws InterruptedException;
}
