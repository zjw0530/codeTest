//package com.exe.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class RabbitmqConfig {
//
//    //交换机用于重新分配队列
//    @Bean
//    DirectExchange exchange() {
//        return new DirectExchange("exchange");
//    }
//
//    //用于延时消费的队列
//    @Bean
//    public Queue repeatTradeQueue() {
//        Queue queue = new Queue("repeatTradeQueue",true,false,false);
//        return queue;
//    }
//
//    //绑定交换机并指定routing key
//    @Bean
//    public Binding repeatTradeBinding() {
//        return BindingBuilder.bind(repeatTradeQueue()).to(exchange()).with("repeatTradeQueue");
//    }
//
//
//    //配置死信队列
//    @Bean
//    public Queue deadLetterQueue() {
//        Map<String,Object> args = new HashMap<>();
////        args.put("x-message-ttl", 3000);
//        args.put("x-dead-letter-exchange", "exchange");
//        args.put("x-dead-letter-routing-key", "repeatTradeQueue");
//        return new Queue("deadLetterQueue", true, false, false, args);
//    }
//}
