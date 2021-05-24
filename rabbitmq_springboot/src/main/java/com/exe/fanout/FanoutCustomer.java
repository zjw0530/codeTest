package com.exe.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,exchange=@Exchange(value = "logs",type = "fanout"))
    })
    public void recevie1(String message){
        System.out.println("recevie1，fanout模型："+message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,exchange = @Exchange(value = "logs",type = "fanout"))
    })
    public void recevie2(String message){
        System.out.println("recevie2，fanout模型："+message);
    }




}
