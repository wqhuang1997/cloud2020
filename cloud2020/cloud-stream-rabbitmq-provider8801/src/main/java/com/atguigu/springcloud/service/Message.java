package com.atguigu.springcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import java.util.UUID;


@EnableBinding(Source.class) //定义消息的推送管道
public class Message implements IMessage{

    @Autowired
    private MessageChannel output; //消息发送管道

    @Override
    public String send() {
        UUID uuid = UUID.randomUUID();
        output.send(MessageBuilder.withPayload(uuid).build());
        System.out.println("uuid:" + uuid);
        return null;
    }
}
