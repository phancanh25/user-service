package com.example.demo.mappers;

import com.example.demo.dtos.AttachmentDTO;
import com.example.demo.dtos.MessageDTO;
import com.example.demo.entities.Attachment;
import com.example.demo.entities.Message;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

  public Message toEntity(MessageDTO messageDTO) {
    Message message = new Message();
    message.setId(messageDTO.getId());
    message.setTimestamp(messageDTO.getTimestamp());
    message.setConversation(messageDTO.getConversation());
    message.setAttachments(messageDTO.getAttachments().stream()
        .map(this::toEntity)
        .collect(Collectors.toList()));
    return message;
  }

  public Attachment toEntity(AttachmentDTO attachmentDTO) {
    Attachment attachment = new Attachment();
    attachment.setUrl(attachmentDTO.getUrl());
    attachment.setContentType(attachmentDTO.getContentType());
    attachment.setName(attachmentDTO.getName());
    attachment.setSize(attachmentDTO.getSize());
    return attachment;
  }
}
