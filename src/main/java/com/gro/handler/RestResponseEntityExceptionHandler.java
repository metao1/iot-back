package com.gro.handler;

import com.gro.exception.*;
import com.gro.model.ApiError;
import com.gro.model.ErrorResponse;
import com.gro.model.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+jsonhateoas")
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> notFoundException(final NotFoundException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<ApiError> inUsedException(final NotFoundException e) {
        return error(e, HttpStatus.IM_USED, e.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ApiError> unAuthorizedException(final NotFoundException e) {
        return error(e, HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(LoginAlreadyUsedException.class)
    public ResponseEntity<ApiError> loginInUsedException(final NotFoundException e) {
        return error(e, HttpStatus.IM_USED, e.getMessage());
    }

    @ExceptionHandler(TaskException.class)
    public ResponseEntity<ApiError> serverExceptionHandler(final TaskException e) {
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<ApiError> invalidLoginExceptionHandler(final TaskException e) {
        return error(e, HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(AccountResourceException.class)
    public ResponseEntity<ApiError> accountResourceNotFoundExceptionHandler(final TaskException e) {
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    private ResponseEntity<ApiError> error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new ApiError(httpStatus, message, message), httpStatus);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> assertionException(final IllegalArgumentException exception) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return error(exception, HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        extendHeaders(headers);

        return ResponseEntity.status(status).body(ErrorResponse
            .newBuilder()
            .withCode(status.toString())
            .withDescription(ex.getMessage())
            .withMessage(status.getReasonPhrase())
            .withErrorCode(status.toString())
            .build());
    }

    private void extendHeaders(HttpHeaders headers) {

    }
}
