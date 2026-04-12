package com.example.tasktracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Setter
@Getter
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

}
