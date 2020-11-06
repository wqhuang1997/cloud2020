package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "${seata.storage}")
public interface StorageService {

    @RequestMapping(value = "/storage/decrease", method = RequestMethod.POST)
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);

//
//    @RequestMapping(value = "/storage/get", method = RequestMethod.POST)
//    String get();

}
