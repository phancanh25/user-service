package com.example.demo.controllers;

import com.example.demo.services.MessageStatisticsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class MessageStatisticsController {

  @Autowired
  private MessageStatisticsService messageStatisticsService;

  @GetMapping("/textMessages")
  public ResponseEntity<Long> getTextMessagesCount(
      @RequestParam Long startTime,
      @RequestParam Long endTime,
      @RequestParam List<String> userPhoneNumbers) {
    long count = messageStatisticsService.countTextMessagesByTimeRangeAndUsers(startTime, endTime, userPhoneNumbers);
    return ResponseEntity.ok(count);
  }

  @GetMapping("/attachments")
  public ResponseEntity<Long> getAttachmentsCount(
      @RequestParam Long startTime,
      @RequestParam Long endTime,
      @RequestParam List<String> userPhoneNumbers) {

    long count = messageStatisticsService.countAttachmentsByTimeRangeAndUsers(startTime, endTime, userPhoneNumbers);
    return ResponseEntity.ok(count);
  }
}
