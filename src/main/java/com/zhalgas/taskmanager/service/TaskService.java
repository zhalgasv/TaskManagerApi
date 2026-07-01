package com.zhalgas.taskmanager.service;

import com.zhalgas.taskmanager.dto.TaskRequest;
import com.zhalgas.taskmanager.dto.TaskResponse;
import com.zhalgas.taskmanager.entity.Task;
import com.zhalgas.taskmanager.entity.TaskStatus;
import com.zhalgas.taskmanager.exception.TaskNotFoundException;
import com.zhalgas.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskResponse> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TaskResponse create(TaskRequest request){
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus() != null ? request.getStatus() : TaskStatus.TODO)
                .deadline(request.getDeadline())
                .createdAt(LocalDateTime.now())
                .build();

        return toResponse(taskRepository.save(task));
    }

    public TaskResponse findById(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return toResponse(task);
    }

    private TaskResponse toResponse(Task task){
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .deadline(task.getDeadline())
                .build();
    }


    public TaskResponse update(Long id, TaskRequest request){
        Task task = getTask(id);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setDeadline(request.getDeadline());
        return toResponse(taskRepository.save(task));
    }

    public void delete(Long id) {
        getTask(id);
        taskRepository.deleteById(id);
    }

    private Task getTask(Long id){
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }


}
