package com.iot.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidLoginException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public InvalidLoginException() {
    super("Invalid login");
  }
}
