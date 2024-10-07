package com.example.demo.dtos;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
  private String id;
  private long timestamp;
  private String conversation;
  private List<AttachmentDTO> attachments;
}
