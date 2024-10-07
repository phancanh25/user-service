package com.example.demo.dtos;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDTO {
  private String id;
  private String name;
  private long timestamp;
  private ChatType type;
  private List<MemberDTO> members;

  // Getters and Setters
  public enum ChatType {
    GROUP,
    INDIVIDUAL
  }
}
