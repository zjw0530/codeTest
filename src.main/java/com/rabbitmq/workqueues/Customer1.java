package com.rabbitmq.workqueues;

import com.rabbitmq.client.*;
import com.rabbitmq.utils.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtils.getConnection();

        final Channel channel = connection.createChannel();

        channel.basicQos(1);
        channel.queueDeclare("holle",true,false,false,null);
        channel.basicConsume("holle",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("================="+new String(body));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /**
                 * 手动确认
                 * 1：确认哪个消息
                 * 2：是否开启手动确认多消息
                 */
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
