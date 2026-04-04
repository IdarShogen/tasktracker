package com.example.tasktracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Имя обязательно для заполнения")
    private String title;

    @Column
    @NotNull(message = "Статус не может быть Null")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    @FutureOrPresent(message = "Дата не может быть раньше сегодняшней")
    @NotNull(message = "Дата не может быть Null")
    private LocalDate deadline;

    @Column
    @Size(max = 1000, message = "Текст не должен превышать 1000 символов")
    private String description;

    public Task(){}

    public Task(Long id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
