package com.example.demo.service;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.entity.Task;
import com.example.demo.entity.TaskStatus;
import com.example.demo.repository.TaskRepository;
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
                .map(this::toResponce)
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

        return toResponce(taskRepository.save(task));
    }

    public TaskResponse findById(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: " + id));
        return toResponce(task);
    }

    private TaskResponse toResponce(Task task){
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .deadline(task.getDeadline())
                .build();
    }


}
