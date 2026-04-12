package com.example.tasktracker.service;

import com.example.tasktracker.model.Status;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.repository.TaskRepository;
import org.springframework.data.domain.Sort;
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

    public List<Task> sort(String field, String direction, Status status, String title) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        Sort sort = Sort.by(sortDirection, field);
        if(status != null && title == null) {
            return repository.findByStatus(status, sort);
        } else if(status == null && title != null) {
            return repository.findByTitle(title, sort);
        } else if(status != null && title != null) {
            return null;
        } else {
            return repository.findAll(sort);
        }

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

    public void updateAllTasks(Task newTask) {
        List<Task> tasks = getAllTasks();
        for(Task task : tasks) {
            task.setTitle(newTask.getTitle());
            task.setStatus(newTask.getStatus());
            task.setDeadline(newTask.getDeadline());
            task.setDescription(newTask.getDescription());

            repository.save(task);
        }
    }

    public void deleteTaskById(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllTasks() {
        repository.deleteAll();
    }
}
