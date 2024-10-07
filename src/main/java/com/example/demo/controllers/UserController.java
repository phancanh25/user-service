package com.example.demo.controllers;
import com.example.demo.dtos.MemberDTO;
import com.example.demo.entities.Member;
import com.example.demo.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private MemberService userService;

  @PostMapping("/filters")
  public ResponseEntity<?> filterUser(@RequestBody MemberDTO userDTO, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
    if (authorization == null || !authorization.startsWith("Bearer ")) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized request");
    }
    Member foundUser = userService.filterUser(userDTO.getPhoneNumber());
    if (foundUser != null) {
      return ResponseEntity.ok(foundUser);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
  }

  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody MemberDTO userDTO, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
    if (authorization == null || !authorization.startsWith("Bearer ")) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized request");
    }
    if (userDTO.getPhoneNumber() == null || userDTO.getPhoneNumber().isEmpty() ||
        userDTO.getName() == null || userDTO.getName().isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Phone number and name are required");
    }
    Member existingUser = userService.filterUser(userDTO.getPhoneNumber());
    if (existingUser != null) {
      return ResponseEntity.ok(existingUser); // User already exists
    }
    Member createdUser = userService.createUser(userDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
  }
}
