package com.gro.web.controller;

import com.gro.model.ApiError;
import com.gro.model.NotFoundException;
import com.gro.model.notification.NotificationNotFoundException;
import com.gro.model.rpicomponent.exception.*;
import com.gro.web.service.storage.StorageException;
import com.gro.web.service.storage.StorageFileNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class GlobalControllerExceptionHandler {

    @ExceptionHandler({
            NotificationNotFoundException.class,
            RPiComponentNotFoundException.class,
            RPiPinNotFoundException.class,
            EntityNotFoundException.class
    })
    public ResponseEntity<Object> handleRPiComponentNotFound(NotFoundException e, WebRequest request) {
        String error = e.getMessage();
        ApiError apiError =
                new ApiError(HttpStatus.NOT_FOUND, e.getLocalizedMessage());

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(InvalidRPiComponentTypeException.class)
    public ResponseEntity<Object> handleInvalidRPiComponentType(InvalidRPiComponentTypeException e, WebRequest request) {
        String error = e.getMessage();
        ApiError apiError =
                new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, e.getLocalizedMessage());

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(InvalidRelayStateException.class)
    public ResponseEntity<Object> handleInvalidRelayState(InvalidRelayStateException e, WebRequest request) {
        String error = e.getMessage();
        ApiError apiError =
                new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, e.getLocalizedMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({
            StorageException.class,
            StorageFileNotFoundException.class
    })
    public ResponseEntity<Object> handleStorageException(StorageException e, WebRequest request) {
        String error = e.getMessage();
        ApiError apiError =
                new ApiError(HttpStatus.CONFLICT, e.getLocalizedMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
