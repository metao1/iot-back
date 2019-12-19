package com.iot.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.IM_USED)
public class EmailAlreadyUsedException extends BadRequestException {

  private static final long serialVersionUID = 1L;

  public EmailAlreadyUsedException() {
    super(ErrorConstants.EMAIL_ALREADY_USED_TYPE, "Email is already in use!", "userManagement", "emailexists");
  }
}
