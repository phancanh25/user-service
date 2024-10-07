package com.example.demo.controllers;

import com.example.demo.services.MessageStatisticsService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MessageStatisticsControllerTest {

  @Mock
  private MessageStatisticsService messageStatisticsService;

  @InjectMocks
  private MessageStatisticsController messageStatisticsController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetTextMessagesCount() {
    Long startTime = 1609459200000L; // Example start time
    Long endTime = 1640995200000L; // Example end time
    List<String> userPhoneNumbers = Arrays.asList("123456789", "987654321");

    when(messageStatisticsService.countTextMessagesByTimeRangeAndUsers(startTime, endTime, userPhoneNumbers))
        .thenReturn(10L); // Mock response

    ResponseEntity<Long> response = messageStatisticsController.getTextMessagesCount(startTime, endTime, userPhoneNumbers);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(10L, response.getBody());
  }

  @Test
  public void testGetAttachmentsCount() {
    Long startTime = 1609459200000L; // Example start time
    Long endTime = 1640995200000L; // Example end time
    List<String> userPhoneNumbers = Collections.singletonList("123456789");

    when(messageStatisticsService.countAttachmentsByTimeRangeAndUsers(startTime, endTime, userPhoneNumbers))
        .thenReturn(5L); // Mock response

    ResponseEntity<Long> response = messageStatisticsController.getAttachmentsCount(startTime, endTime, userPhoneNumbers);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(5L, response.getBody());
  }
}
