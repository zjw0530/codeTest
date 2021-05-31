package com.exe.ddl;

import java.time.LocalDateTime;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
@Component
public class DeadLetterSender {
 
    @Autowired
    private AmqpTemplate rabbitTemplate;
 
 
    public void send(String msg) {
        System.out.println("DeadLetterSender 发送时间:"+LocalDateTime.now().toString()+" msg内容："+msg);
        rabbitTemplate.convertAndSend("deadLetterQueue", msg);
    }

    public void send(String msg,long time) {
        System.out.println("DeadLetterSender 发送时间:"+LocalDateTime.now().toString()+" msg内容："+msg);
        MessagePostProcessor mess = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(String.valueOf(time));
                return message;
            }
        };
        rabbitTemplate.convertAndSend("deadLetterQueue",(Object)msg,mess);
    }
 
}