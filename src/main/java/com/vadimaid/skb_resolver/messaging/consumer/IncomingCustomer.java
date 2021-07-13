package com.vadimaid.skb_resolver.messaging.consumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomingCustomer {
    private String login;
    private String email;
    private String fullName;
}
