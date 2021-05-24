package com.exe;

import com.sun.xml.internal.ws.api.policy.ModelGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class RabbitMqPrivate {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testHello(){
        rabbitTemplate.convertAndSend("hello","hello word");
    }

    @Test
    public void testWord(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("word","word 模型");
        }
    }
    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("logs","","fanout模型");
    }

    @Test
    public void testDirect(){
        rabbitTemplate.convertAndSend("directs","info","direct模型");
    }

    @Test
    public void topic(){
        rabbitTemplate.convertAndSend("topics","user.save.prodect","user.save.prodect，topic模型");
    }
}
