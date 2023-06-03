package com.kohn.apitodo.services;

import com.kohn.apitodo.dto.TaskDto;
import com.kohn.apitodo.exceptions.TaskNotFoundException;
import com.kohn.apitodo.exceptions.TaskNotOwnedByUserException;
import com.kohn.apitodo.exceptions.UserNotFoundException;
import com.kohn.apitodo.mappers.TaskMapper;
import com.kohn.apitodo.models.Task;
import com.kohn.apitodo.models.User;
import com.kohn.apitodo.repositories.TaskRepository;
import com.kohn.apitodo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Transactional
    public TaskDto createTask(Long userId, TaskDto taskDto) {
        LOGGER.info("Attempting to create task for user with ID: {}", userId);

        User user = getUserById(userId);

        if (taskDto.getDueDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Due date should be in the future");
        }

        Task task = taskMapper.toTask(taskDto);
        task.setUser(user);

        Task savedTask = taskRepository.save(task);
        LOGGER.info("Task created successfully for user with ID: {}", userId);

        return taskMapper.toTaskDto(savedTask);
    }

    public Page<TaskDto> findTasksByUserId(Long userId, Pageable pageable) {
        LOGGER.info("Attempting to find tasks for user with ID: {}", userId);

        if (!userRepository.existsById(userId)) {
            LOGGER.error("User with ID: {} not found", userId);
            throw new UserNotFoundException(userId);
        }

        LOGGER.info("Tasks retrieved successfully for user with ID: {}", userId);

        return taskRepository.findByUserId(userId, pageable)
                .map(taskMapper::toTaskDto);
    }

    @Transactional
    public TaskDto updateTask(Long userId, Long taskId, TaskDto taskDto) {
        LOGGER.info("Attempting to update task with ID: {} for user with ID: {}", taskId, userId);

        Task task = getTaskById(taskId);

        if (!task.getUser().getId().equals(userId)) {
            LOGGER.error("Task with ID: {} does not belong to user with ID: {}", taskId, userId);
            throw new TaskNotOwnedByUserException(taskId, userId);
        }

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());
        task.setPriority(taskDto.getPriority());
        task.setStatus(taskDto.getStatus());

        Task updatedTask = taskRepository.save(task);

        LOGGER.info("Task with ID: {} updated successfully for user with ID: {}", taskId, userId);

        return taskMapper.toTaskDto(updatedTask);
    }

    @Transactional
    public void deleteTaskById(Long userId, Long taskId) {
        LOGGER.info("Attempting to delete task with ID: {} for user with ID: {}", taskId, userId);

        Task task = getTaskById(taskId);

        if (!task.getUser().getId().equals(userId)) {
            LOGGER.error("Task with ID: {} does not belong to user with ID: {}", taskId, userId);
            throw new TaskNotOwnedByUserException(taskId, userId);
        }

        taskRepository.deleteById(taskId);
        LOGGER.info("Task with ID: {} deleted successfully for user with ID: {}", taskId, userId);
    }

    private Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> {
                    LOGGER.error("Task with ID: {} not found", taskId);
                    return new TaskNotFoundException(taskId);
                });
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    LOGGER.error("User with ID: {} not found", userId);
                    return new UserNotFoundException(userId);
                });
    }
}
