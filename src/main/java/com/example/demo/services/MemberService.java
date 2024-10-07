package com.example.demo.services;

import com.example.demo.dtos.MemberDTO;
import com.example.demo.entities.Member;
import com.example.demo.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MemberService {
  @Autowired
  private MemberRepository userRepository;

  @Autowired
  private RestTemplate restTemplate;

  public Member filterUser(String phoneNumber) {
    return userRepository.findByPhoneNumber(phoneNumber);
  }

  public Member createUser(MemberDTO memberDTO) {
    Member member = new Member();
    member.setPhoneNumber(memberDTO.getPhoneNumber());
    member.setName(memberDTO.getName());
    return userRepository.save(member);
  }
}
