package com.example.demo.services;

import com.example.demo.dtos.ChatDTO;
import com.example.demo.dtos.MemberDTO;
import com.example.demo.dtos.MessageContentDTO;
import com.example.demo.dtos.MessageDTO;
import com.example.demo.dtos.ReceiverDTO;
import com.example.demo.dtos.SenderDTO;
import com.example.demo.entities.ChatRoom;
import com.example.demo.entities.GroupChat;
import com.example.demo.entities.IndividualChat;
import com.example.demo.entities.Message;
import com.example.demo.repositories.ChatRoomRepository;
import com.example.demo.repositories.GroupChatRepository;
import com.example.demo.repositories.IndividualChatRepository;
import com.example.demo.repositories.MessageRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MessagingServiceTest {

  @Mock
  private GroupChatRepository groupChatRepository;

  @Mock
  private IndividualChatRepository individualChatRepository;

  @Mock
  private MessageRepository messageRepository;

  @Mock
  private ChatRoomRepository chatRoomRepository;

  @InjectMocks
  private MessagingService messagingService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCreateChatRoom_GroupChat() {
    ChatDTO chatDTO = new ChatDTO();
    chatDTO.setId("123");
    chatDTO.setName("Group Chat");
    chatDTO.setTimestamp(System.currentTimeMillis());
    chatDTO.setType(ChatDTO.ChatType.GROUP);
    List<MemberDTO> members = new ArrayList<>();
    MemberDTO member = new MemberDTO("1234567890", "John");
    members.add(member);
    chatDTO.setMembers(members);

    GroupChat mockGroupChat = new GroupChat();
    mockGroupChat.setId(chatDTO.getId());
    mockGroupChat.setName(chatDTO.getName());
    mockGroupChat.setTimestamp(chatDTO.getTimestamp());
    mockGroupChat.setType(ChatRoom.ChatType.GROUP);

    when(groupChatRepository.save(any(GroupChat.class))).thenReturn(mockGroupChat);

    ChatRoom createdChatRoom = messagingService.createChatRoom(chatDTO);

    assertEquals(mockGroupChat, createdChatRoom);
    verify(groupChatRepository).save(any(GroupChat.class));
  }

  @Test
  public void testCreateChatRoom_IndividualChat() {
    ChatDTO chatDTO = new ChatDTO();
    chatDTO.setId("123");
    chatDTO.setName("Direct Chat");
    chatDTO.setTimestamp(System.currentTimeMillis());
    chatDTO.setType(ChatDTO.ChatType.INDIVIDUAL);
    List<MemberDTO> members = new ArrayList<>();
    MemberDTO member = new MemberDTO("1234567890", "John");
    members.add(member);
    chatDTO.setMembers(members);

    IndividualChat mockIndividualChat = new IndividualChat();
    mockIndividualChat.setId(chatDTO.getId());
    mockIndividualChat.setName(chatDTO.getName());
    mockIndividualChat.setTimestamp(chatDTO.getTimestamp());
    mockIndividualChat.setType(ChatRoom.ChatType.INDIVIDUAL);

    when(individualChatRepository.save(any(IndividualChat.class))).thenReturn(mockIndividualChat);

    ChatRoom createdChatRoom = messagingService.createChatRoom(chatDTO);

    assertEquals(mockIndividualChat, createdChatRoom);
    verify(individualChatRepository).save(any(IndividualChat.class));
  }

  @Test
  public void testImportMessage_CreatesNewChatRoom() {
    MessageContentDTO messageContentDTO = new MessageContentDTO();
    SenderDTO senderDTO = new SenderDTO();
    senderDTO.setPhoneNumber("123");
    senderDTO.setDisplayName("none");
    messageContentDTO.setSender(senderDTO);
    ReceiverDTO receiverDTO = new ReceiverDTO();
    receiverDTO.setId("123");
    receiverDTO.setType("GROUP");
    messageContentDTO.setReceiver(receiverDTO);
    MessageDTO messageDTO = new MessageDTO();
    messageDTO.setId("123");
    messageDTO.setTimestamp(System.currentTimeMillis());
    messageDTO.setConversation("Hello!");
    messageContentDTO.setMessage(messageDTO);

    when(chatRoomRepository.findByIdAndType("123", ChatRoom.ChatType.GROUP)).thenReturn(Optional.empty());

    ChatRoom createdChatRoom = new GroupChat();
    createdChatRoom.setId("123");
    createdChatRoom.setType(ChatRoom.ChatType.GROUP);
    createdChatRoom.setName("Group Chat");

    when(chatRoomRepository.save(any(ChatRoom.class))).thenReturn(createdChatRoom);
    when(messageRepository.save(any(Message.class))).thenAnswer(invocation -> invocation.getArgument(0));
  }

  @Test
  public void testImportMessage_UsesExistingChatRoom() {
    MessageContentDTO messageContentDTO = new MessageContentDTO();
    ReceiverDTO receiverDTO = new ReceiverDTO();
    receiverDTO.setId("123");
    receiverDTO.setType("INDIVIDUAL");

    messageContentDTO.setReceiver(receiverDTO);
    MessageDTO messageDTO = new MessageDTO();
    messageDTO.setId("123");
    messageDTO.setTimestamp(System.currentTimeMillis());
    messageDTO.setConversation("Hello!");
    messageContentDTO.setMessage(messageDTO);

    ChatRoom existingChatRoom = new IndividualChat();
    existingChatRoom.setId("123");
    existingChatRoom.setType(ChatRoom.ChatType.INDIVIDUAL);

    when(chatRoomRepository.findByIdAndType("123", ChatRoom.ChatType.INDIVIDUAL)).thenReturn(Optional.of(existingChatRoom));
    when(messageRepository.save(any(Message.class))).thenAnswer(invocation -> invocation.getArgument(0));
  }
}
