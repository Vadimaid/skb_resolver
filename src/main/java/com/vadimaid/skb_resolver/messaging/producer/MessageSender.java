package com.vadimaid.skb_resolver.messaging.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageSender {

    @Value("${messaging.rabbit.producer.queue}")
    private String messagingQueue;

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(OutgoingMessage message) {
        if (Objects.isNull(message)) {
            log.error("Cannot send empty message");
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            log.info("Trying to send message to {}", messagingQueue);
            rabbitTemplate.convertAndSend(messagingQueue,
                    mapper.writeValueAsString(message));
            log.info("Message sent to queue { " + message + " }");
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
