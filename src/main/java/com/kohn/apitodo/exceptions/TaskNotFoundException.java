package com.kohn.apitodo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Task Not Found")
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long taskId) {
        super("Task with id " + taskId + " not found.");
    }
}
