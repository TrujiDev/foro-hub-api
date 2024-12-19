package com.trujidev.forohub.foro_hub_api.infrastructure.errors;

import com.trujidev.forohub.foro_hub_api.infrastructure.exception.ExceptionValidation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleErrors {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity handleError404() {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity handleError400(MethodArgumentNotValidException e) {
    var errors = e.getFieldErrors().stream().map(DatosErrorValidation::new).toList();
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(ExceptionValidation.class)
  public ResponseEntity handleExceptionErrors(ExceptionValidation e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }

  private record DatosErrorValidation(String field, String typeError){
    public DatosErrorValidation(FieldError error) {
      this(error.getField(), error.getDefaultMessage());
    }
  }

}
