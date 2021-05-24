package com.rabbitmq.hollwork;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.utils.RabbitMqUtils;
import org.junit.Test;
import sun.plugin2.message.CookieOpMessage;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Provider {

    @Test
    public void testSendMessage() throws IOException, TimeoutException {
        Connection connection = RabbitMqUtils.getConnection();
        //获取连接中通道
        Channel channel = connection.createChannel();
        /**
         * 通道绑定对应消息队列
         * 1：队列名称，不存在自动创建
         * 2：队列特性是否持久化 true、false
         * 3：exclusive 是否占用队列  true：占用 、false
         * 4：autoDelete 是否在消费后删除队列  true、false
         * 5：额外附加参数
         */
        channel.queueDeclare("Holle",false,false,false,null);

        //发布消息
        /**
         * 1：交换机名称
         * 2：队列名称
         * 3：传递消息额外设置
         * 4：消息的具体内容
         */
        channel.basicPublish("","Holle",null,"hello.rabbitmq".getBytes());

        channel.close();
        connection.close();

    }
}
