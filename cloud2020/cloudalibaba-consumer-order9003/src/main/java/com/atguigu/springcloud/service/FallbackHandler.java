package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;

public class FallbackHandler {

    public static CommonResult FeginHandler(Integer id) {
        return new CommonResult(44444, "消费者内部出错");

    }
}
