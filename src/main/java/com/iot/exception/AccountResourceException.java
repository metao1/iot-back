package com.iot.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountResourceException extends RuntimeException {
  public AccountResourceException(String message) {
    super(message);
  }
}
