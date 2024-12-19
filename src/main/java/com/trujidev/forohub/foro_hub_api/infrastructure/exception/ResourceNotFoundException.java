package com.trujidev.forohub.foro_hub_api.infrastructure.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
