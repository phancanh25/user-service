package com.example.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReceiverDTO {
  private String type; // GROUP or INDIVIDUAL
  private String id;
}
