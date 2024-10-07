package com.example.demo.repositories;

import com.example.demo.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
  Member findByPhoneNumber(String phoneNumber);
}
