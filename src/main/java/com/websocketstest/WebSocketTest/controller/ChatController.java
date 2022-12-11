package com.websocketstest.WebSocketTest.controller;

import com.websocketstest.WebSocketTest.model.BasicMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

@Controller
public class ChatController
{
    @Autowired
    private SimpMessageSendingOperations sendOps;

    private Scanner scan = null;

    @MessageMapping("/results.ask")
    @SendTo("/client/results.done")
    public BasicMessage sendMessage(@Payload final BasicMessage message) throws Exception {
        String uuid = message.getUuid();

        if (uuid == null) {
            throw new Exception("No uuid provided.");
        }

        String folder = "D:\\Development\\devs-web-env\\lome-files\\visualizations";
        Path path = Paths.get(folder, uuid, "messages.log");
        File file = new File(path.toString());

        // TODO: This should be moved in another message.
        if (scan == null) scan = new Scanner(file);

        int i = 0;

        while (scan.hasNextLine() && i < 10) {
            sendOps.convertAndSend("/client/results.send",  scan.nextLine());
            i++;
        }

        if (!scan.hasNextLine()) scan.close();

        return message;
    }
}
