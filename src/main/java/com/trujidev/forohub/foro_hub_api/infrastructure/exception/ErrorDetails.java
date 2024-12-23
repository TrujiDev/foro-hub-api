package com.trujidev.forohub.foro_hub_api.infrastructure.exception;

import lombok.Getter;

@Getter
public class ErrorDetails {
  private int statusCode;
  private String message;
  private String details;

  public ErrorDetails(int statusCode, String message, String details) {
    this.statusCode = statusCode;
    this.message = message;
    this.details = details;
  }
}
