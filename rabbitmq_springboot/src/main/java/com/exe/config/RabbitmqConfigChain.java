package com.exe.config;

import com.exe.entity.ProcessReceiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitmqConfigChain {

    /**
     * 死信队列  TTL配置在消息上的缓冲队列。
     */
    public static final String DELAY_QUEUE_PER_MESSAGE_TTL_NAME = "deadPerMessageTtlQueue";

    /**
     * 实际消费交换机
     */
    private static final String DELAY_EXCHANGE_NAME = "exchange";

    /**
     * 实际消费队列。
     */
    private static final String DELAY_PROCESS_QUEUE_NAME = "repeatTradeQueue";

    /**
     * 死信队列  TTL配置在队列上的缓冲队列。
     */
    private static final String DELAY_QUEUE_PER_QUEUE_TTL_NAME = "deadPerQueueTtlQueue";

    /**
     * 过期时间
     */
    private static final String QUEUE_EXPIRATION = "5000";

    /**
     *
     * @return
     */
    @Bean
    @Primary
    Queue delayQueuePerMessageTTL() {
        return QueueBuilder.durable(DELAY_QUEUE_PER_MESSAGE_TTL_NAME)
                .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE_NAME) // DLX，dead letter发送到的exchange
                .withArgument("x-dead-letter-routing-key", DELAY_PROCESS_QUEUE_NAME) // dead letter携带的routing key
                .build();
    }

//    @Bean("delayQueuePerQueueTTL")
//    Queue delayQueuePerQueueTTL() {
//        return QueueBuilder.durable(DELAY_QUEUE_PER_QUEUE_TTL_NAME)
//                .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE_NAME) // DLX
//                .withArgument("x-dead-letter-routing-key", DELAY_PROCESS_QUEUE_NAME) // dead letter携带的routing key
//                .withArgument("x-message-ttl", QUEUE_EXPIRATION) // 设置队列的过期时间
//                .build();
//    }

    /**
     * 需要配置DLX
     * @return
     */
    @Bean
    @Primary
    Queue delayProcessQueue() {
        return QueueBuilder.durable(DELAY_PROCESS_QUEUE_NAME)
                .build();
    }


    /**
     * 然后再将该DLX绑定到实际消费队列即delay_process_queue上。这样所有的死信都会通过DLX被转发到delay_process_queue：
     * @param delayProcessQueue
     * @param delayExchange
     * @return
     */
    @Bean
    Binding dlxBinding(Queue delayProcessQueue, DirectExchange delayExchange) {
        return BindingBuilder.bind(delayProcessQueue)
                .to(delayExchange)
                .with(DELAY_PROCESS_QUEUE_NAME);
    }


    @Bean
    @Primary
    SimpleMessageListenerContainer processContainer(ConnectionFactory connectionFactory, ProcessReceiver processReceiver) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(DELAY_PROCESS_QUEUE_NAME); // 监听delay_process_queue
        container.setMessageListener(new MessageListenerAdapter(processReceiver));
        return container;
    }
}
