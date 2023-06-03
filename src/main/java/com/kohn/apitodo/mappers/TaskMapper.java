package com.kohn.apitodo.mappers;

import com.kohn.apitodo.dto.TaskDto;
import com.kohn.apitodo.models.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDto toTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();

        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setDueDate(task.getDueDate());
        taskDto.setPriority(task.getPriority());
        taskDto.setStatus(task.getStatus());

        return taskDto;
    }

    public Task toTask(TaskDto taskDto) {
        Task task = new Task();

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());
        task.setPriority(taskDto.getPriority());
        task.setStatus(taskDto.getStatus());

        return task;
    }
}
