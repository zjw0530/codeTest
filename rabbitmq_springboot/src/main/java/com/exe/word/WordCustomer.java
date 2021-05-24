package com.exe.word;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WordCustomer {

    @RabbitListener(queuesToDeclare = @Queue("word"))
    public void receive1(String message){
        System.out.println("receiel:"+message);
    }

    @RabbitListener(queuesToDeclare = @Queue("word"))
    public void receive2(String messgae){
        System.out.println("receie2:"+messgae);
    }
}
