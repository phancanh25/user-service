package com.example.demo.repositories;

import com.example.demo.entities.ChatRoom;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
  Optional<ChatRoom> findByIdAndType(String id, ChatRoom.ChatType type);
}
