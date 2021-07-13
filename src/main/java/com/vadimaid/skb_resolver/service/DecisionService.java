package com.vadimaid.skb_resolver.service;

import com.vadimaid.skb_resolver.messaging.consumer.IncomingCustomer;

public interface DecisionService {
    void makeDecision(IncomingCustomer customer);
}
