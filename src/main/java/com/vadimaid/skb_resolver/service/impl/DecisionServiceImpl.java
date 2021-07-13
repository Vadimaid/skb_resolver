package com.vadimaid.skb_resolver.service.impl;

import com.vadimaid.skb_resolver.messaging.consumer.IncomingCustomer;
import com.vadimaid.skb_resolver.messaging.producer.MessageSender;
import com.vadimaid.skb_resolver.messaging.producer.OutgoingMessage;
import com.vadimaid.skb_resolver.service.DecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class DecisionServiceImpl implements DecisionService {

    private final MessageSender messageSender;

    @Override
    public void makeDecision(IncomingCustomer customer) {
        OutgoingMessage result = new OutgoingMessage();
        result.setEmail(customer.getEmail());
        result.setMessageText("Successfully approved!");
        if (decline()) {
            result.setMessageText("Sorry your application was declined");
        }
        messageSender.sendMessage(result);
    }

    private boolean decline() {
        return new Random().nextInt(10) == 1;
    }
}
