package com.rabbitmq.topics;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.RabbitMqUtils;
import javafx.scene.input.TouchEvent;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics","topic");
        String routerKey = "*.use";
        channel.basicPublish("topics",routerKey,null,("这里是topics动态路由，routerKey："+ routerKey).getBytes());
        RabbitMqUtils.closeConnectionAndChanel(channel,connection);
    }
}
