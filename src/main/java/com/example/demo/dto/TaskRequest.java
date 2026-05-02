package com.example.demo.dto;


import com.example.demo.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private TaskStatus status;

    private LocalDateTime deadline;
}
