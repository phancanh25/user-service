package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "chat_room")
public class ChatRoom {

  @Id
  private String id;

  private String name;

  private long timestamp;

  @Enumerated(EnumType.STRING)
  private ChatType type;

  public ChatRoom() {
  }

  // Getters and Setters
  public enum ChatType {
    GROUP,
    INDIVIDUAL
  }
}
