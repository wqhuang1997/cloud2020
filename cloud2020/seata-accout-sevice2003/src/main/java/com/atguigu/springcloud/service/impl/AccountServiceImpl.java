package com.atguigu.springcloud.service.impl;


import com.atguigu.springcloud.dao.AccountDao;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public CommonResult decrease(Long userId, BigDecimal money) throws InterruptedException {
        //TimeUnit.SECONDS.sleep(20);
        accountDao.decrease(userId, money);
        return new CommonResult(200, "扣除yue成功");
    }
}
