package com.rabbitmq.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.RabbitMqParam;
import com.rabbitmq.utils.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        String routerKey = "·~direct";
        channel.exchangeDeclare(RabbitMqParam.EXCHANGE_NAME,"direct");
        channel.basicPublish(RabbitMqParam.EXCHANGE_NAME,"error",null,("direct模型发布的基于router key 消息："+routerKey).getBytes());
        RabbitMqUtils.closeConnectionAndChanel(channel,connection);
    }
}
