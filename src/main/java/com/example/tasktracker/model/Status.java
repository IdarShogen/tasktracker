package com.example.tasktracker.model;

import jakarta.persistence.Enumerated;
import org.hibernate.annotations.JdbcType;


public enum Status {
    INACTIVE,
    IN_PROGRESS,
    DONE
}
