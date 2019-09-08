package com.iot.model.rpicomponent.exception;

import com.iot.model.NotFoundException;

public class RPiPinNotFoundException extends NotFoundException {

    private static final long serialVersionUID = -2544295686792571862L;

    public RPiPinNotFoundException(String message) {
        super(message);
    }

}
