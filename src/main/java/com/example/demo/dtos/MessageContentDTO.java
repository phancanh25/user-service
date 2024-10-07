package com.example.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageContentDTO {
  private SenderDTO sender;
  private ReceiverDTO receiver;
  private MessageDTO message;
}
