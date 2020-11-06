package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelController {

    @GetMapping("/testB")
    public String test() throws InterruptedException {
        //Thread.sleep(1000);
        int d = 2/0;
        return "testB ------------------";

    }

    @GetMapping("/testA")
    public String testA() throws InterruptedException {
        //Thread.sleep(5000);
        return "testA ---------------------";

    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "q1") String q1,
                             @RequestParam(value = "q2") String q2) {
        return "hotKey -----------" + q1 + "-----------" + q2;
    }

    public String deal_testHotKey(String q1, String q2, BlockException b) {
        return "deal_testHotKey -----------";
    }

}
