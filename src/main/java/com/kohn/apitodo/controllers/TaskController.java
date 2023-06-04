package com.kohn.apitodo.controllers;

import com.kohn.apitodo.dtos.TaskDto;
import com.kohn.apitodo.services.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<TaskDto> createTaskForUser(@PathVariable Long userId, @Valid @RequestBody TaskDto taskDto) {
        TaskDto createdTask = taskService.createTask(userId, taskDto);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<TaskDto>> getTasksByUserId(@PathVariable Long userId, @PageableDefault(size = 10) Pageable pageable) {
        Page<TaskDto> tasks = taskService.findTasksByUserId(userId, pageable);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/user/{userId}/{taskId}")
    public ResponseEntity<TaskDto> updateTaskForUser(@PathVariable Long userId, @PathVariable Long taskId, @Valid @RequestBody TaskDto taskDto) {
        TaskDto updatedTask = taskService.updateTask(userId, taskId, taskDto);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/user/{userId}/{taskId}")
    public ResponseEntity<Void> removeTask(@PathVariable Long userId, @PathVariable Long taskId) {
        taskService.deleteTaskById(userId, taskId);
        return ResponseEntity.noContent().build();
    }
}
