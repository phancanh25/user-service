package com.example.demo.controllers;

import com.example.demo.dtos.ChatDTO;
import com.example.demo.dtos.MessageContentDTO;
import com.example.demo.dtos.SenderDTO;
import com.example.demo.entities.ChatRoom;
import com.example.demo.entities.Message;
import com.example.demo.services.MessagingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MessagingControllerTest {

  @Mock
  private MessagingService messagingService;

  @InjectMocks
  private MessagingController messagingController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCreateChatRoom() {
    ChatDTO chatDTO = new ChatDTO();
    chatDTO.setName("Group Chat");
    chatDTO.setId("chat-123");

    ChatRoom mockChatRoom = new ChatRoom();
    mockChatRoom.setId("chat-123");
    mockChatRoom.setName("Group Chat");

    when(messagingService.createChatRoom(chatDTO)).thenReturn(mockChatRoom);

    ResponseEntity<ChatRoom> response = messagingController.createChatRoom(chatDTO);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(mockChatRoom, response.getBody());
  }

  @Test
  public void testImportMessage() {
    MessageContentDTO messageContentDTO = new MessageContentDTO();
    SenderDTO senderDTO = new SenderDTO();
    senderDTO.setDisplayName("user");
    senderDTO.setPhoneNumber("123");
    messageContentDTO.setSender(senderDTO);

    Message mockMessage = new Message();
    mockMessage.setId("msg-123");

    when(messagingService.importMessage(messageContentDTO)).thenReturn(mockMessage);

    ResponseEntity<Message> response = messagingController.importMessage(messageContentDTO);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(mockMessage, response.getBody());
  }
}
