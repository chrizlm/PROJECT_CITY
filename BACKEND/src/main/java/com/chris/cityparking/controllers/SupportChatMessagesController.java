package com.chris.cityparking.controllers;


import com.chris.cityparking.modules.SupportChatMessages;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "http://localhost:3000/")
public class SupportChatMessagesController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public SupportChatMessages sendMessage(@Payload SupportChatMessages supportChatMessages) {
        return supportChatMessages;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public SupportChatMessages addUser(@Payload SupportChatMessages supportChatMessages, SimpMessageHeaderAccessor headerAccessor) {

// Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", supportChatMessages.getSender());
        return supportChatMessages;
    }
}