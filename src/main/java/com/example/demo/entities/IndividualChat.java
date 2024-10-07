package com.example.demo.entities;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "individual_chat")
public class IndividualChat extends ChatRoom {

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "individual_chat_id")
  private List<Member> members;
}
