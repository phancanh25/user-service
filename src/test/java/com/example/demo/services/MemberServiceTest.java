package com.example.demo.services;

import com.example.demo.dtos.MemberDTO;
import com.example.demo.entities.Member;
import com.example.demo.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MemberServiceTest {

  @Mock
  private MemberRepository memberRepository;

  @Mock
  private RestTemplate restTemplate; // Not used in this test but can be included for future tests

  @InjectMocks
  private MemberService memberService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testFilterUser_UserExists() {
    String phoneNumber = "84762627371";
    Member mockMember = new Member();
    mockMember.setId(1L);
    mockMember.setPhoneNumber(phoneNumber);
    mockMember.setName("John Doe");

    // Mock the repository to return the member
    when(memberRepository.findByPhoneNumber(phoneNumber)).thenReturn(mockMember);

    Member foundMember = memberService.filterUser(phoneNumber);

    assertEquals(mockMember, foundMember);
  }

  @Test
  public void testFilterUser_UserNotFound() {
    String phoneNumber = "84762627371";

    // Mock the repository to return null
    when(memberRepository.findByPhoneNumber(phoneNumber)).thenReturn(null);

    Member foundMember = memberService.filterUser(phoneNumber);

    assertEquals(null, foundMember);
  }

}
