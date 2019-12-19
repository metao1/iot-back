package com.iot.model.rpicomponent.exception;

import com.iot.model.NotFoundException;

public class RPiComponentNotFoundException extends NotFoundException {

    private static final long serialVersionUID = 5009555411974113907L;

    public RPiComponentNotFoundException(String message) {
        super(message);
    }

}
