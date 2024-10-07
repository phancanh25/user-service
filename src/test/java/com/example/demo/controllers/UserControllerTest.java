package com.example.demo.controllers;

import com.example.demo.dtos.MemberDTO;
import com.example.demo.entities.Member;
import com.example.demo.services.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserControllerTest {

  @Mock
  private MemberService userService;

  @InjectMocks
  private UserController userController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testFilterUser_UserExists() {
    String phoneNumber = "84762627371";
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setPhoneNumber(phoneNumber);

    Member mockMember = new Member();
    mockMember.setId(1L);
    mockMember.setPhoneNumber(phoneNumber);
    mockMember.setName("John Doe");
    when(userService.filterUser(phoneNumber)).thenReturn(mockMember);
    ResponseEntity<?> response = userController.filterUser(memberDTO, "Bearer valid_token");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(mockMember, response.getBody());
  }

  @Test
  public void testFilterUser_UserNotFound() {
    String phoneNumber = "84762627371";
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setPhoneNumber(phoneNumber);
    when(userService.filterUser(phoneNumber)).thenReturn(null);
    ResponseEntity<?> response = userController.filterUser(memberDTO, "Bearer valid_token");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("User not found", response.getBody());
  }

  @Test
  public void testFilterUser_UnauthorizedRequest() {
    String phoneNumber = "84762627371";
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setPhoneNumber(phoneNumber);
    ResponseEntity<?> response = userController.filterUser(memberDTO, null);
    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals("Unauthorized request", response.getBody());
  }

  @Test
  public void testCreateUser_UserAlreadyExists() {
    String phoneNumber = "84762627371";
    String name = "John Doe";
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setPhoneNumber(phoneNumber);
    memberDTO.setName(name);
    Member existingMember = new Member();
    existingMember.setId(1L);
    existingMember.setPhoneNumber(phoneNumber);
    existingMember.setName(name);
    when(userService.filterUser(phoneNumber)).thenReturn(existingMember);
    ResponseEntity<?> response = userController.createUser(memberDTO, "Bearer valid_token");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(existingMember, response.getBody());
  }

  @Test
  public void testCreateUser_UserCreatedSuccessfully() {
    String phoneNumber = "84762627371";
    String name = "John Doe";
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setPhoneNumber(phoneNumber);
    memberDTO.setName(name);
    Member newMember = new Member();
    newMember.setId(2L);
    newMember.setPhoneNumber(phoneNumber);
    newMember.setName(name);
    when(userService.filterUser(phoneNumber)).thenReturn(null);
    when(userService.createUser(memberDTO)).thenReturn(newMember);
    ResponseEntity<?> response = userController.createUser(memberDTO, "Bearer valid_token");
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(newMember, response.getBody());
  }

  @Test
  public void testCreateUser_MissingPhoneNumber() {
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setName("John Doe");
    ResponseEntity<?> response = userController.createUser(memberDTO, "Bearer valid_token");
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Phone number and name are required", response.getBody());
  }

  @Test
  public void testCreateUser_UnauthorizedRequest() {
    String phoneNumber = "84762627371";
    String name = "John Doe";
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setPhoneNumber(phoneNumber);
    memberDTO.setName(name);
    ResponseEntity<?> response = userController.createUser(memberDTO, null);
    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    assertEquals("Unauthorized request", response.getBody());
  }
}
