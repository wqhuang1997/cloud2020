package com.atguigu.springcloud.service;

import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.entities.CommonResult;

import java.util.concurrent.TimeoutException;

public interface OrderService {

    CommonResult create(Order order) throws Exception;

//    String get();
}
