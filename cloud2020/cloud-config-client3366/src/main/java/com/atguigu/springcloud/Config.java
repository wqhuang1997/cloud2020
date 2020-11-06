package com.atguigu.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
public class Config {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/getInfo")
    public String getConfigInfo() {
        return configInfo;
    }
}
