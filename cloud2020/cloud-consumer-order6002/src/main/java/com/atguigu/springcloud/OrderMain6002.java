package com.atguigu.springcloud;

//import com.atguigu.springcloud.rule.MyselfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(excludeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.atguigu.springcloud.rule")
        })
//@RibbonClient(name = "cloud-payment-service", configuration = MyselfRule.class)
public class OrderMain6002 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain6002.class, args);
    }
}
