package com.kohn.apitodo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Task Not Owned By User")
public class TaskNotOwnedByUserException extends RuntimeException {
    public TaskNotOwnedByUserException(Long taskId, Long userId) {
        super("Task with id " + taskId + " does not belong to user with id " + userId);
    }
}
