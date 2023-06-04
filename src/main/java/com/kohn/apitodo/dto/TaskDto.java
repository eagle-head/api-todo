package com.kohn.apitodo.dto;

import com.kohn.apitodo.enums.TaskPriority;
import com.kohn.apitodo.enums.TaskStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskDto(
        Long id,
        @NotBlank(message = "Title is mandatory") String title,
        @NotBlank(message = "Description is mandatory") String description,
        @Future(message = "Due date should be in the future")
        @NotNull(message = "Due date priority is mandatory") LocalDateTime dueDate,
        @NotNull(message = "Task priority is mandatory") TaskPriority priority,
        @NotNull(message = "Task status is mandatory") TaskStatus status) {
}
