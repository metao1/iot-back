package com.iot.model.rpicomponent.exception;

import com.iot.model.NotFoundException;

public class EntityNotFoundException extends NotFoundException {

    private static final long serialVersionUID = 6104607355884857925L;

    public EntityNotFoundException(String message) {
        super(message);
    }

}
