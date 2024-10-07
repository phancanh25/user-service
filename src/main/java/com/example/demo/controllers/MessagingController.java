package com.example.demo.controllers;

import com.example.demo.dtos.ChatDTO;
import com.example.demo.dtos.MessageContentDTO;
import com.example.demo.dtos.MessageDTO;
import com.example.demo.entities.ChatRoom;
import com.example.demo.entities.Message;
import com.example.demo.services.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messaging")
public class MessagingController {

  @Autowired
  private MessagingService messagingService;

  @PostMapping("/chat-room")
  public ResponseEntity<ChatRoom> createChatRoom(@RequestBody ChatDTO chatDTO) {
    ChatRoom chatRoom = messagingService.createChatRoom(chatDTO);
    return new ResponseEntity<>(chatRoom, HttpStatus.CREATED);
  }

  @PostMapping("/import-message")
  public ResponseEntity<Message> importMessage(@RequestBody MessageContentDTO messageContentDTO) {
    Message message = messagingService.importMessage(messageContentDTO);
    return ResponseEntity.ok(message);
  }
}

