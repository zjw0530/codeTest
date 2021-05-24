package com.exe.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicCostomer {
    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,exchange =@Exchange(value = "topics",type = "topic"),key = {"user.*"})
    })
    public void recevie1(String message){
        System.out.println("recevie1,"+message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue,exchange = @Exchange(value = "topics",type = "topic"),key = {"user.#"})
    })
    public void  recevie2(String message){
        System.out.println("recevie2:"+message);
    }
}
