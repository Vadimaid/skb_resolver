package com.vadimaid.skb_resolver.service.impl;

import com.vadimaid.skb_resolver.messaging.consumer.IncomingCustomer;
import com.vadimaid.skb_resolver.messaging.producer.MessageSender;
import com.vadimaid.skb_resolver.messaging.producer.OutgoingMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DecisionServiceImplTest {

    @InjectMocks
    private DecisionServiceImpl decisionService;

    @Mock
    MessageSender messageSender;

    @Test
    public void testMakeDecision() {
        doNothing().when(messageSender).sendMessage(any(OutgoingMessage.class));

        IncomingCustomer customer = new IncomingCustomer();
        customer.setLogin("Test");
        customer.setEmail("test@gmail.com");
        customer.setFullName("Test Test");

        verify(decisionService, times(1)).makeDecision(customer);
    }

}