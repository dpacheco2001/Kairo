package com.kairo.taskservice.repository;

import com.kairo.taskservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}