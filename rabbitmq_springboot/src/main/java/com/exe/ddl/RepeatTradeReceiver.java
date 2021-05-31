package com.exe.ddl;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
 
@Component
@RabbitListener(queues = "repeatTradeQueue")
public class RepeatTradeReceiver {
    
    @RabbitHandler
    public void process(String msg) {
        System.out.println("repeatTradeQueue 接收时间:"+LocalDateTime.now().toString()+" 接收内容:"+msg);
    }
 
}
