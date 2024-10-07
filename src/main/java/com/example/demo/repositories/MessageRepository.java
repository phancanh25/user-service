package com.example.demo.repositories;

import com.example.demo.entities.Message;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

  @Query("SELECT COUNT(m) FROM Message m " +
      "JOIN m.chatRoom cr " +
      "JOIN Member mb ON (cr.type = 'GROUP' OR cr.type = 'INDIVIDUAL') " +
      "WHERE m.timestamp BETWEEN :startTimestamp AND :endTimestamp " +
      "AND (mb.phoneNumber IN :userPhoneNumbers)")
  long countTextMessagesByTimeRangeAndUsers(
      @Param("startTimestamp") long startTimestamp,
      @Param("endTimestamp") long endTimestamp,
      @Param("userPhoneNumbers") List<String> userPhoneNumbers);

  @Query(value = "SELECT COUNT(a.url) FROM message m " +
      "JOIN attachment a ON m.id = a.message_id " +
      "JOIN chat_room cr ON m.chat_room_id = cr.id " +
      "JOIN member mb ON cr.id = mb.chat_room_id " +
      "WHERE a.content_type LIKE 'text/%' " +
      "AND m.timestamp BETWEEN :startTimestamp AND :endTimestamp " +
      "AND mb.phone_number IN (:phoneNumbers)", nativeQuery = true)
  long countAttachmentsByTimeRangeAndUsers(
      @Param("startTimestamp") long startTimestamp,
      @Param("endTimestamp") long endTimestamp,
      @Param("phoneNumbers") List<String> phoneNumbers);
}
