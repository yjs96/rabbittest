package com.example.inventoryservice.listener;

import com.example.inventoryservice.dto.OrderDto;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = "order-queue")
public class OrderListener {

//    @RabbitHandler
//    public void handleOrder(OrderDto orderDto) {
//        System.out.println("Received order: " + orderDto.getOrderId());
//    }

//    @RabbitListener(queues = "order-queue")
//    public void handleMessage(String message) {
//        System.out.println("Received message: " + message);
//    }


    // Direct Exchange Listeners
    @RabbitListener(queues = "direct.queue1")
    public void handleDirectQueue1(String message) {
        System.out.println("Received from direct.queue1: " + message);
    }

    @RabbitListener(queues = "direct.queue2")
    public void handleDirectQueue2(String message) {
        System.out.println("Received from direct.queue2: " + message);
    }

    // Fanout Exchange Listeners
    @RabbitListener(queues = "fanout.queue1")
    public void handleFanoutQueue1(String message) {
        System.out.println("Received from fanout.queue1: " + message);
    }

    @RabbitListener(queues = "fanout.queue2")
    public void handleFanoutQueue2(String message) {
        System.out.println("Received from fanout.queue2: " + message);
    }

    // Topic Exchange Listeners
    @RabbitListener(queues = "topic.queue1")
    public void handleTopicQueue1(String message) {
        System.out.println("Received from topic.queue1 (*.key.*): " + message);
    }

    @RabbitListener(queues = "topic.queue2")
    public void handleTopicQueue2(String message) {
        System.out.println("Received from topic.queue2 (topic.#): " + message);
    }

    @RabbitListener(queues = "topic.queue3")
    public void handleTopicQueue3(String message) {
        System.out.println("Received from topic.queue3 (topic.key.specific): " + message);
    }

    // Headers Exchange Listeners
    @RabbitListener(queues = "headers.queue1")
    public void handleHeadersQueue1(String message) {
        System.out.println("Received from headers.queue1 (header1): " + message);
    }

    @RabbitListener(queues = "headers.queue2")
    public void handleHeadersQueue2(String message) {
        System.out.println("Received from headers.queue2 (header1 & header2): " + message);
    }
}
