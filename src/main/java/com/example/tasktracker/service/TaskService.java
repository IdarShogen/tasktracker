package com.example.tasktracker.service;

import com.example.tasktracker.model.SortField;
import com.example.tasktracker.model.Status;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.repository.TaskRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
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

    public Sort initSort(String field, String direction) {
        try {
            SortField sortField = SortField.valueOf(field.toUpperCase());
            Sort.Direction sortDirection;
            if(direction != null && direction.equalsIgnoreCase("desc")) sortDirection = Sort.Direction.DESC;
            else if(direction != null && direction.equalsIgnoreCase("asc")) sortDirection = Sort.Direction.ASC;
            else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid direction");

            return Sort.by(sortDirection, String.valueOf(sortField).toLowerCase());

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid sort field");
        }
    }

    public List<Task> getFilteredAndSortedTasks(String field, String direction, String title, Status status) {
        Sort sort = initSort(field, direction);
        if (status != null && title == null) {
            return repository.findByStatus(status, sort);
        } else if (status == null && title != null) {
            return repository.findByTitle(title, sort);
        } else if (status != null) {
            return repository.findByStatusAndTitle(status, title, sort);
        } else {
            return repository.findAll(sort);
        }
    }

    public void addTask(Task task) {
        repository.save(task);
    }

    public void updateTask(Long id, Task newTask) {
        Task oldTask = repository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        oldTask.setTitle(newTask.getTitle());
        oldTask.setStatus(newTask.getStatus());
        oldTask.setDeadline(newTask.getDeadline());
        oldTask.setDescription(newTask.getDescription());
        repository.save(oldTask);

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
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        repository.deleteById(id);
    }

    public void deleteAllTasks() {
        repository.deleteAll();
    }
}
