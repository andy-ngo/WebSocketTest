package com.websocketstest.WebSocketTest.controller;

import com.websocketstest.WebSocketTest.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.util.Scanner;

@Controller
public class ChatController
{
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload final ChatMessage chatMessage)
    {
        String message = chatMessage.getContent();
        String data = null;
        if (message.equals("test"))
        {
            try{
                File resultFile = new File("results.txt");
                Scanner reader = new Scanner(resultFile);
                data = reader.nextLine();
                chatMessage.setContent(data);
            } catch(FileNotFoundException e) {
                data = "No file found";
                e.printStackTrace();
                chatMessage.setContent(data);
            }
            return chatMessage;
        }
        return chatMessage;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/public")
    public ChatMessage newUser(@Payload final ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor)
    {
        headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
        return chatMessage;
    }
}
