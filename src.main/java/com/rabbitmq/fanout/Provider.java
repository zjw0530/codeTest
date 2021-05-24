package com.rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();

        //声明交换机  1:交换机名  2：交换机类型  fanout：广播
        channel.exchangeDeclare("logs","fanout");

        channel.basicPublish("logs","",null,"fanout type message".getBytes());

        RabbitMqUtils.closeConnectionAndChanel(channel,connection);
    }
}
