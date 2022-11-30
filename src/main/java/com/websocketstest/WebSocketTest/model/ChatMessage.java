package com.websocketstest.WebSocketTest.model;

import lombok.Builder;
import lombok.Getter;
//use this for the model results
@Builder
public class ChatMessage
{
	private MessageType type;
    private String content;
    private String sender;
    private String time;
    
    public ChatMessage(MessageType type, String content, String sender, String time) {
    	this.type = type;
    	this.content = content;
    	this.sender = sender;
    	this.time = time;
    }
    
    public MessageType getType()
    {
        return this.type;
    }
    
    public void setType(MessageType type)
    {
        this.type = type;
    }

    public String getContent()
    {
    	return this.content;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getSender()
    {
        return this.sender;
    }
    
    public void setSender(String sender)
    {
        this.sender = sender;
    }

    public String getTime()
    {
    	return this.time;
    }
    
    public void setTime(String time)
    {
        this.time = time;
    }
}
