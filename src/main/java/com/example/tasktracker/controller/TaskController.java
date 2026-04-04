package com.example.tasktracker.controller;
import com.example.tasktracker.model.Status;
import com.example.tasktracker.model.Task;

import com.example.tasktracker.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Valid
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }


    @GetMapping("GET /tasks")
    public List<Task> getTasks() {
        return service.getAllTasks();
    }

    @GetMapping("GET /tasks/{id}")
    public Task getTask(@PathVariable Long id) {
        return service.getTask(id);
    }

    @GetMapping("/tasks")
    public List<Task> findByStatus(@RequestParam(required = false) Status status) {
        return service.findByStatus(status);
    }



    @PostMapping("POST /tasks")
    public void addTask(@RequestBody @Valid Task task) {
        service.addTask(task);
    }

    @PutMapping("UPDATE /tasks/{id}")
    public void updateTask(@PathVariable Long id,@RequestBody @Valid Task newTask) {
        service.updateTask(id, newTask);
    }

    @DeleteMapping("DELETE /tasks/{id}")
    public void deleteTaskById(@PathVariable Long id) {
        service.deleteTaskById(id);
    }

    @DeleteMapping("DELETE /tasks")
    public void deleteAllTasks() {
        service.deleteAllTasks();
    }

}
