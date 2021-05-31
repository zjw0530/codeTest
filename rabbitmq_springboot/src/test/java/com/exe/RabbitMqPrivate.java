package com.exe;

import com.exe.ddl.DeadLetterSender;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitMqPrivate {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DeadLetterSender deadLetterSender;

    @Test
    public void testHello(){
        rabbitTemplate.convertAndSend("hello","hello word");
    }

    /**
     * word模型
     */
    @Test
    public void testWord(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("word","word 模型");
        }
    }

    /**
     * fanout模型
     */
    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("logs","","fanout模型");
    }

    /***
     * direct模型
     */
    @Test
    public void testDirect(){
        rabbitTemplate.convertAndSend("directs","info","direct模型");
    }

    /**
     *
     * topic模型
     */
    @Test
    public void topic(){
        rabbitTemplate.convertAndSend("topics","user.save.prodect","user.save.prodect，topic模型");
    }


    @Test
    public void deadTest() {
        deadLetterSender.send("队列设置过期时间测试");
    }

    @Test
    public void DeadLetterTest() {
        deadLetterSender.send("消息设置过期时间测试",5000);
    }



//    @Test
//    public void testDelayQueuePerMessageTTL() throws InterruptedException {
//        ProcessReceiver.latch = new CountDownLatch(3);
//        for (int i = 1; i <= 3; i++) {
//            long expiration = i * 1000;
//            rabbitTemplate.convertAndSend(RabbitmqConfigChain.DELAY_QUEUE_PER_MESSAGE_TTL_NAME,
//                    (Object) ("Message From delay_queue_per_message_ttl with expiration " + expiration), new ExpirationMessagePostProcessor(expiration));
//        }
//        ProcessReceiver.latch.await();
//    }
}
