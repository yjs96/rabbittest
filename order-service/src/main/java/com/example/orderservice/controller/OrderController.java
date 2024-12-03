package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class OrderController {

    private final RabbitTemplate rabbitTemplate;

//    public OrderController(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }

//    @PostMapping
//    public String createOrder(@RequestBody OrderDto orderDto) {
//        rabbitTemplate.convertAndSend("order-exchange", "order-routing-key", orderDto);
//        return "Order sent to inventory service";
//    }

//    @PostMapping
//    public String sendMessage(@RequestParam String message) {
//        rabbitTemplate.convertAndSend("order-exchange", "order-routing-key", message);
//        return "Message sent: " + message;
//    }

    public OrderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Direct Exchange Test
    @PostMapping("/direct/{key}")
    public String sendDirectMessage(@PathVariable String key, @RequestParam String message) {
        rabbitTemplate.convertAndSend("direct.exchange", "direct." + key, message);
        return "Sent to direct exchange with key: direct." + key;
    }

    // Fanout Exchange Test
    @PostMapping("/fanout")
    public String sendFanoutMessage(@RequestParam String message) {
        rabbitTemplate.convertAndSend("fanout.exchange", "", message);
        return "Sent to fanout exchange";
    }

    // Topic Exchange Test
    @PostMapping("/topic/{key}")
    public String sendTopicMessage(@PathVariable String key, @RequestParam String message) {
        rabbitTemplate.convertAndSend("topic.exchange", "topic." + key, message);
        return "Sent to topic exchange with key: topic." + key;
    }

    // Headers Exchange Test
    @PostMapping("/headers")
    public String sendHeadersMessage(@RequestParam String message,
                                     @RequestParam(required = false) boolean header1,
                                     @RequestParam(required = false) boolean header2) {
        MessageProperties messageProperties = new MessageProperties();
        if (header1) messageProperties.setHeader("header1", "value1");
        if (header2) messageProperties.setHeader("header2", "value2");

        Message msg = new Message(message.getBytes(), messageProperties);
        rabbitTemplate.send("headers.exchange", "", msg);
        return "Sent to headers exchange";
    }
}
