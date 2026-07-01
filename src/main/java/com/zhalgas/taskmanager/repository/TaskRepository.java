package com.zhalgas.taskmanager.repository;

import com.zhalgas.taskmanager.entity.Task;
import com.zhalgas.taskmanager.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
}