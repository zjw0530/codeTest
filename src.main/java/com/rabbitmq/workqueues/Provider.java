package com.rabbitmq.workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("holle",true,false,false,null);
        for (int i = 1; i <= 10; i++) {
            channel.basicPublish("","holle",null,"holle".getBytes());
        }
        RabbitMqUtils.closeConnectionAndChanel(channel,connection);
    }
}
