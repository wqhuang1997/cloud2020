package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;

public interface StorageService {

    CommonResult decrease(Long productId, Integer count);

}
