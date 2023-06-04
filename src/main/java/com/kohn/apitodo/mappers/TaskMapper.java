package com.kohn.apitodo.mappers;

import com.kohn.apitodo.dto.TaskDto;
import com.kohn.apitodo.models.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskDto toTaskDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus());
    }

    public Task toTask(TaskDto taskDto) {
        Task task = new Task();

        task.setTitle(taskDto.title());
        task.setDescription(taskDto.description());
        task.setDueDate(taskDto.dueDate());
        task.setPriority(taskDto.priority());
        task.setStatus(taskDto.status());

        return task;
    }
}
