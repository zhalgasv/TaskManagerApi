package com.zhalgas.taskmanager.dto;


import com.zhalgas.taskmanager.entity.TaskStatus;
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
