package com.example.demo.entities;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Attachment {
  private String url;
  private String contentType;
  private String name;
  private long size;

  public Attachment() {
  }

  public Attachment(String url, String contentType, String name, long size) {
    this.url = url;
    this.contentType = contentType;
    this.name = name;
    this.size = size;
  }
}