package com.rabbitmq.hollwork;

import com.rabbitmq.client.*;
import com.rabbitmq.utils.RabbitMqUtils;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("Holle",false,false,false,null);

        /**
         * 1:消息队列名称
         * 2:开始消息的自动确认机制
         * 3：消费时的回调接口
         */
        channel.basicConsume("Holle",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("=============="+new String(body));
            }
        });

    }
}
