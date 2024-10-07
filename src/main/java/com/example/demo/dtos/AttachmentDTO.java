package com.example.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentDTO {
  private String url;
  private String contentType;
  private String name;
  private long size;
}
