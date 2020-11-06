package com.atguigu.springcloud;

import com.atguigu.springcloud.service.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    @Autowired
    private IMessage message;

    @GetMapping("/sendMsg")
    public String sendMsg() {
        return message.send();

    }}
