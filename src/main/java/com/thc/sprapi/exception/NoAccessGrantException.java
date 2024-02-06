package com.thc.sprapi.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN)
@SuppressWarnings("serial")
@NoArgsConstructor
public class NoAccessGrantException extends RuntimeException {
    public NoAccessGrantException(String message) {
        super(message);
    }
}
