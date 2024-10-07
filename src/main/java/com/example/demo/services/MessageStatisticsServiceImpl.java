package com.example.demo.services;

import com.example.demo.repositories.MessageRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageStatisticsServiceImpl implements MessageStatisticsService{
  @Autowired
  private MessageRepository messageRepository;

  @Override
  public long countTextMessagesByTimeRangeAndUsers(Long startTime, Long endTime, List<String> userPhoneNumbers) {
    return messageRepository.countTextMessagesByTimeRangeAndUsers(startTime, endTime, userPhoneNumbers);
  }

  @Override
  public long countAttachmentsByTimeRangeAndUsers(Long startTime, Long endTime, List<String> userPhoneNumbers) {
  return messageRepository.countAttachmentsByTimeRangeAndUsers(startTime, endTime, userPhoneNumbers);
  }
}
