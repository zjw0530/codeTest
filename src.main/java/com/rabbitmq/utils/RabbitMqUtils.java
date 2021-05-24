package com.rabbitmq.utils;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqUtils {
    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("106.13.145.253");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");

        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("ems");

        return connectionFactory.newConnection();
    }

    public static void closeConnectionAndChanel(Channel channel, Connection connection) throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
