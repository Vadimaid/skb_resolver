package com.vadimaid.skb_resolver.messaging.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vadimaid.skb_resolver.service.DecisionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageReceiver {

    private final DecisionService decisionService;

    @RabbitListener(queues = "${messaging.rabbit.consumer.queue}")
    public void receive (Message message) {
        ObjectMapper mapper = new ObjectMapper();
        String body = new String(message.getBody());
        try {
            IncomingCustomer receivedMessage = mapper.readValue(body, IncomingCustomer.class);
            log.info("Message received {}", receivedMessage);
            decisionService.makeDecision(receivedMessage);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
