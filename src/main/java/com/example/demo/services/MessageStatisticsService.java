package com.example.demo.services;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface MessageStatisticsService {
  long countTextMessagesByTimeRangeAndUsers(Long startTime, Long endTime, List<String> userPhoneNumbers);
  long countAttachmentsByTimeRangeAndUsers(Long startTime, Long endTime, List<String> userPhoneNumbers);
}
