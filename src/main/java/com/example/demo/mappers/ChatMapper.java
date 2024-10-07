package com.example.demo.mappers;

import com.example.demo.dtos.ChatDTO;
import com.example.demo.dtos.MemberDTO;
import com.example.demo.entities.ChatRoom;
import com.example.demo.entities.GroupChat;
import com.example.demo.entities.IndividualChat;
import com.example.demo.entities.Member;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ChatMapper {

  public ChatRoom toEntity(ChatDTO chatDTO) {
    if ("GROUP".equals(chatDTO.getType().name())) {
      GroupChat groupChat = new GroupChat();
      groupChat.setId(chatDTO.getId());
      groupChat.setName(chatDTO.getName());
      groupChat.setTimestamp(chatDTO.getTimestamp());
      groupChat.setMembers(chatDTO.getMembers().stream()
          .map(this::toEntity)
          .collect(Collectors.toList()));
      return groupChat;
    } else if ("INDIVIDUAL".equals(chatDTO.getType().name())) {
      IndividualChat individualChat = new IndividualChat();
      individualChat.setId(chatDTO.getId());
      individualChat.setTimestamp(chatDTO.getTimestamp());
      individualChat.setMembers(chatDTO.getMembers().stream()
          .map(this::toEntity)
          .collect(Collectors.toList()));
      return individualChat;
    }
    return null;
  }

  public Member toEntity(MemberDTO memberDTO) {
    Member member = new Member();
    member.setPhoneNumber(memberDTO.getPhoneNumber());
    member.setName(memberDTO.getName());
    return member;
  }
}
