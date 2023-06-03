package com.kohn.apitodo.dto;

import com.kohn.apitodo.enums.TaskPriority;
import com.kohn.apitodo.enums.TaskStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @Future(message = "Due date should be in the future")
    @NotNull(message = "Due date priority is mandatory")
    private LocalDateTime dueDate;

    @NotNull(message = "Task priority is mandatory")
    private TaskPriority priority;

    @NotNull(message = "Task status is mandatory")
    private TaskStatus status;
}
