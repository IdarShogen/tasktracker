package com.example.tasktracker.service;

import com.example.tasktracker.model.Status;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTask(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
    }



    public List<Task> findByStatus(Status status) {
        return repository.findByStatus(status);
    }

    public void addTask(Task task) {
        repository.save(task);
    }

    public void updateTask(Long id, Task newTask) {
        Optional<Task> optionalTask = repository.findById(id);

        if(optionalTask.isPresent()) {
            Task oldTask = optionalTask.get();
            oldTask.setTitle(newTask.getTitle());
            oldTask.setStatus(newTask.getStatus());
            oldTask.setDeadline(newTask.getDeadline());
            oldTask.setDescription(newTask.getDescription());

            repository.save(oldTask);
        }
    }

    public void deleteTaskById(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllTasks() {
        repository.deleteAll();
    }
}
