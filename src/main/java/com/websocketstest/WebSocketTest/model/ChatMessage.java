package com.websocketstest.WebSocketTest.model;

import lombok.Builder;
import lombok.Getter;
//use this for the model results
@Builder
public class ChatMessage
{
    @Getter
    private MessageType type;

    @Getter
    private String content;

    @Getter
    private String sender;

    @Getter
    private String time;
}
