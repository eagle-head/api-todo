package com.kohn.apitodo.models;

import com.kohn.apitodo.enums.TaskPriority;
import com.kohn.apitodo.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @Future(message = "Due date should be in the future")
    @NotNull(message = "Due date priority is mandatory")
    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;

    @NotNull(message = "Task priority is mandatory")
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @NotNull(message = "Task status is mandatory")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
