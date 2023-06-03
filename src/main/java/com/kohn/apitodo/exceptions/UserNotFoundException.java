package com.kohn.apitodo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User Not Found")
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("User with id " + userId + " not found.");
    }
}
