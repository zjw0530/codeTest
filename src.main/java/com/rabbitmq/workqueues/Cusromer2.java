package com.rabbitmq.workqueues;

import com.rabbitmq.client.*;
import com.rabbitmq.utils.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Cusromer2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtils.getConnection();

        final Channel channel = connection.createChannel();
        channel.basicQos(1);//一次只能消费一条
        channel.queueDeclare("holle",true,false,false,null);
        channel.basicConsume("holle",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("====="+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

    }
}
