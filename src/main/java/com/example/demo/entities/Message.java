package com.example.demo.entities;

import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "message")
public class Message {
  @Id
  private String id; // Message ID

  private long timestamp;

  private String conversation;

  @ElementCollection
  @CollectionTable(name = "attachment", joinColumns = @JoinColumn(name = "message_id"))
  private List<Attachment> attachments;

  @ManyToOne
  @JoinColumn(name = "chat_room_id")
  private ChatRoom chatRoom;
}
