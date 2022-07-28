package com.tackedev.springboottemplate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import java.time.Instant;

public class ErrorResponseEntity extends ResponseEntity<ErrorResponseEntity.ErrorPayload> {

    public ErrorResponseEntity(@NonNull HttpStatus status, @NonNull String message, Throwable cause) {
        super(new ErrorPayload(status, message, cause), status);
    }

    public ErrorResponseEntity(@NonNull HttpStatus status, @NonNull String message) {
        super(new ErrorPayload(status, message), status);
    }

    public record ErrorPayload(int status, @NonNull String message, Throwable cause, Instant timestamp) {
        public ErrorPayload(@NonNull HttpStatus httpStatus, @NonNull String message, Throwable cause) {
            this(httpStatus.value(), message, cause, Instant.now());
        }

        public ErrorPayload(@NonNull HttpStatus httpStatus, @NonNull String message) {
            this(httpStatus.value(), message, null, Instant.now());
        }
    }
}
