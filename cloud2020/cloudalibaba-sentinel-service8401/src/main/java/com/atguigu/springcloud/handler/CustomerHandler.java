package com.atguigu.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

public class CustomerHandler {

    public static CommonResult customerHandler(BlockException b) {
        return new CommonResult(500, b.getClass().getCanonicalName() + "\t 服务不可用");
    }

    public static CommonResult customerHandler2(BlockException b) {
        return new CommonResult(400, b.getClass().getCanonicalName() + "\t 服务不可用");
    }
}
