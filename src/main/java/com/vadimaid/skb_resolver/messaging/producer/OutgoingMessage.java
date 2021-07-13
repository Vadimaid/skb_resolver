package com.vadimaid.skb_resolver.messaging.producer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutgoingMessage {
    private String email;
    private String messageText;
}
