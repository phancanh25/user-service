package com.example.demo.services;

import com.example.demo.dtos.ChatDTO;
import com.example.demo.dtos.MessageContentDTO;
import com.example.demo.dtos.ReceiverDTO;
import com.example.demo.entities.Attachment;
import com.example.demo.entities.ChatRoom;
import com.example.demo.entities.GroupChat;
import com.example.demo.entities.IndividualChat;
import com.example.demo.entities.Member;
import com.example.demo.entities.Message;
import com.example.demo.mappers.MessageMapper;
import com.example.demo.repositories.ChatRoomRepository;
import com.example.demo.repositories.GroupChatRepository;
import com.example.demo.repositories.IndividualChatRepository;
import com.example.demo.repositories.MessageRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessagingService {

  @Autowired
  private GroupChatRepository groupChatRepository;

  @Autowired
  private IndividualChatRepository individualChatRepository;

  @Autowired
  private MessageMapper messageMapper;

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private ChatRoomRepository chatRoomRepository;

  public ChatRoom createChatRoom(ChatDTO chatDTO) {
    if (chatDTO.getType() == ChatDTO.ChatType.GROUP) {
      GroupChat groupChat = new GroupChat();
      groupChat.setId(chatDTO.getId());
      groupChat.setName(chatDTO.getName());
      groupChat.setTimestamp(chatDTO.getTimestamp());
      groupChat.setType(ChatRoom.ChatType.GROUP);
      groupChat.setMembers(chatDTO.getMembers().stream()
          .map(memberDTO -> new Member(memberDTO.getPhoneNumber(), memberDTO.getName()))
          .collect(Collectors.toList()));

      return groupChatRepository.save(groupChat);

    } else {
      IndividualChat individualChat = new IndividualChat();
      individualChat.setId(chatDTO.getId());
      individualChat.setName(chatDTO.getName());
      individualChat.setTimestamp(chatDTO.getTimestamp());
      individualChat.setType(ChatRoom.ChatType.INDIVIDUAL);
      individualChat.setMembers(chatDTO.getMembers().stream()
          .map(memberDTO -> new Member(memberDTO.getPhoneNumber(), memberDTO.getName()))
          .collect(Collectors.toList()));

      return individualChatRepository.save(individualChat);
    }
  }

  @Transactional
  public Message importMessage(MessageContentDTO messageContentDTO) {
    ChatRoom chatRoom = createOrGetChatRoom(messageContentDTO.getReceiver());
    Message message = new Message();
    message.setId(messageContentDTO.getMessage().getId());
    message.setTimestamp(messageContentDTO.getMessage().getTimestamp());
    message.setConversation(messageContentDTO.getMessage().getConversation());
    message.setChatRoom(chatRoom);

    List<Attachment> attachments = messageContentDTO.getMessage().getAttachments().stream()
        .map(a -> new Attachment(a.getUrl(), a.getContentType(), a.getName(), a.getSize()))
        .collect(Collectors.toList());

    message.setAttachments(attachments);

    return messageRepository.save(message);
  }

  private ChatRoom createOrGetChatRoom(ReceiverDTO receiverDTO) {
    return chatRoomRepository.findByIdAndType(receiverDTO.getId(), ChatRoom.ChatType.valueOf(receiverDTO.getType()))
        .orElseGet(() -> {
          ChatRoom chatRoom = new ChatRoom();
          chatRoom.setId(receiverDTO.getId());
          chatRoom.setType(ChatRoom.ChatType.valueOf(receiverDTO.getType()));
          chatRoom.setName(receiverDTO.getType().equals("GROUP") ? "Group Chat" : "Direct Chat");

          return chatRoomRepository.save(chatRoom);
        });
  }

}
