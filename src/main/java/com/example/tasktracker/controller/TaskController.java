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


    @GetMapping("/tasks")
    public List<Task> getTasks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Status status,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return service.getFilteredAndSortedTasks(sortBy, direction, title, status);
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable Long id) {
        return service.getTask(id);
    }



    @PostMapping("/tasks")
    public void addTask(@RequestBody @Valid Task task) {
        service.addTask(task);
    }


    @PutMapping("/tasks/{id}")
    public void updateTask(@PathVariable Long id,@RequestBody @Valid Task newTask) {
        service.updateTask(id, newTask);
    }

    @PutMapping("/tasks")
    public void updateAllTasks(@RequestBody @Valid Task newTask) {
        service.updateAllTasks(newTask);
    }


    @DeleteMapping("/tasks/{id}")
    public void deleteTaskById(@PathVariable Long id) {
        service.deleteTaskById(id);
    }

    @DeleteMapping("/tasks")
    public void deleteAllTasks() {
        service.deleteAllTasks();
    }

}
