package com.todoapp.todo_list_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long taskId;
    private String title;
    private String description;
    private LocalDate due_date;
    private boolean completed;
    private LocalDate created_at;
    private LocalDate updated_at;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
