package com.kairo.taskservice.controller;

import com.kairo.taskservice.entity.Task;
import com.kairo.taskservice.service.TaskService;
import com.kairo.taskservice.dto.TaskDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    private Task dtoToEntity(TaskDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.isCompleted());
        return task;
    }

    private TaskDTO entityToDto(Task task) {
        return new TaskDTO(
            task.getTitle(),
            task.getDescription(),
            task.isCompleted()
        );
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(){
        List<TaskDTO> list = taskService.getAllTasks()
        .stream()
        .map(this::entityToDto)
        .toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id)
                .map(this::entityToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO dto){
        Task task = taskService.createTask(dtoToEntity(dto));
        return ResponseEntity.status(201).body(entityToDto(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO dto){
        Task task = taskService.updateTask(id, dtoToEntity(dto));
        return ResponseEntity.ok(entityToDto(task));
    }


}