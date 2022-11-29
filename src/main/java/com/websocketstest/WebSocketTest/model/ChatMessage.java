package com.websocketstest.WebSocketTest.model;

import lombok.Builder;
import lombok.Getter;

@Builder
public class ChatMessage
{
    @Getter
    private MessageType type;
    public MessageType getType()
    {
        return this.type;
    }

    @Getter
    private String content;

    @Getter
    private String sender;
    public String getSender()
    {
        return this.sender;
    }

    @Getter
    private String time;
}
