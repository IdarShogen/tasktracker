package com.example.tasktracker.repository;

import com.example.tasktracker.model.Status;
import com.example.tasktracker.model.Task;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status, Sort sort);
    List<Task> findByTitle(String title, Sort sort);
    List<Task> findByStatusAndTitle(Status status, String title, Sort sort);

}
