package com.example.demo.controller;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @GetMapping
    public List<TaskResponse> findAll(){
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public TaskResponse findById(@PathVariable Long id){
        return taskService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(@Valid @RequestBody TaskRequest request){
        return taskService.create(request);
    }
}
