package com.atguigu.springcloud.service.impl;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.dao.OrderDao;
import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.AccountService;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private StorageService storageService;

    @Autowired
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "oder", rollbackFor = Exception.class)
    @SentinelResource(value = "customHandler", blockHandler = "blockHandler", fallback = "fallback")
    public CommonResult create(Order order) throws Exception {

        log.info("创建订单---------------------------");
        orderDao.create(order);

        log.info("扣减库存---------------------------");
        storageService.decrease(order.getUserId(), order.getCount());

        log.info("扣减yue---------------------------");
        accountService.decrease(order.getProductId(), order.getMoney());

        log.info("修改订单状态---------------------------");
        orderDao.update(order.getUserId(), 0);


        log.info("超时---------------------------");
        CommonResult timeout = accountService.timeout();


        log.info("调用失败---------------------------");
        if (!timeout.getCode().equals(200)) {
            throw new Exception("出错拉");
        }

//        CommonResult timeout = timeout();

        return timeout;

    }

//    @SentinelResource(value = "customHandler", fallback = "customHandler")
//    public CommonResult timeout() {
//        return accountService.timeout();
//    }

    public CommonResult blockHandler(Order order, BlockException b) {
        return new CommonResult(600, "blockHandler------调用失败");
    }

    public CommonResult fallback(Order order, BlockException b) {
        return new CommonResult(700, "fallback--------调用失败");
    }
//    @Override
//    public String get() {
//        return storageService.get();
//    }
}
