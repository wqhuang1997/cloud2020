package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) throws InterruptedException {
        accountService.decrease(userId, money);
        return new CommonResult(200, "扣除yue成功");

    }

    @PostMapping(value = "/timeOut")
    public CommonResult timeOut() throws InterruptedException {
        Thread.sleep(6000);
        System.out.println("调用order测试超时------------------------------");
        return new CommonResult(200, "success");

    }

}
