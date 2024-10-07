package com.example.demo.repositories;

import com.example.demo.entities.IndividualChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualChatRepository extends JpaRepository<IndividualChat, String> {
}
