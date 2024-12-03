package com.example.orderservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
//    @Bean
//    public MessageConverter messageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setMessageConverter(new Jackson2JsonMessageConverter());
//        return template;
//    }

//    @Bean
//    public DirectExchange orderExchange() {
//        return new DirectExchange("order-exchange");
//    }
//
//    @Bean
//    public Queue orderQueue() {
//        return new Queue("order-queue");
//    }
//
//    @Bean
//    public Binding binding(Queue orderQueue, DirectExchange orderExchange) {
//        return BindingBuilder
//                .bind(orderQueue)
//                .to(orderExchange)
//                .with("order-routing-key");
//    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct.exchange");
    }

    @Bean
    public Queue directQueue1() {
        return new Queue("direct.queue1");
    }

    @Bean
    public Queue directQueue2() {
        return new Queue("direct.queue2");
    }

    @Bean
    public Binding directBinding1() {
        return BindingBuilder
                .bind(directQueue1())
                .to(directExchange())
                .with("direct.key1");
    }

    @Bean
    public Binding directBinding2() {
        return BindingBuilder
                .bind(directQueue2())
                .to(directExchange())
                .with("direct.key2");
    }


    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout.exchange");
    }

    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout.queue1");
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout.queue2");
    }

    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder
                .bind(fanoutQueue1())
                .to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder
                .bind(fanoutQueue2())
                .to(fanoutExchange());
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topic.exchange");
    }

    @Bean
    public Queue topicQueue1() {
        return new Queue("topic.queue1");
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("topic.queue2");
    }

    @Bean
    public Queue topicQueue3() {
        return new Queue("topic.queue3");
    }

    @Bean
    public Binding topicBinding1() {
        return BindingBuilder
                .bind(topicQueue1())
                .to(topicExchange())
                .with("topic.key.*");
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder
                .bind(topicQueue2())
                .to(topicExchange())
                .with("topic.#");
    }

    @Bean
    public Binding topicBinding3() {
        return BindingBuilder
                .bind(topicQueue3())
                .to(topicExchange())
                .with("topic.key.specific");
    }


    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange("headers.exchange");
    }

    @Bean
    public Queue headersQueue1() {
        return new Queue("headers.queue1");
    }

    @Bean
    public Queue headersQueue2() {
        return new Queue("headers.queue2");
    }

    @Bean
    public Binding headersBinding1() {
        return BindingBuilder
                .bind(headersQueue1())
                .to(headersExchange())
                .where("header1").exists();
    }

    @Bean
    public Binding headersBinding2() {
        return BindingBuilder
                .bind(headersQueue2())
                .to(headersExchange())
                .whereAll("header1", "header2").exist();
    }
}
