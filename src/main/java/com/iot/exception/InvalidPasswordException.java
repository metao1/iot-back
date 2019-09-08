package com.iot.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidPasswordException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public InvalidPasswordException() {
    super("Incorrect password");
  }
}
