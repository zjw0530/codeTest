package com.exe.direct;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 监听
 */
@Component
public class directCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,exchange = @Exchange(value = "directs",type = "direct"),key = {"info","error"})
    })
    public void recevie1(String message){
        System.out.println("recevie1:"+message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,exchange = @Exchange(value = "directs",type = "direct"),key = "info")
    })
    public void recevie2(String message){
        System.out.println("recevie2:"+message);
    }
}
