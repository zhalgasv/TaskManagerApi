package com.example.demo.dto;

import com.example.demo.entity.TaskStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime deadline;
}
