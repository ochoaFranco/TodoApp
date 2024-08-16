package com.todoapp.todo_list_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long categoryId;
    @Column(unique = true)
    private String name;
}
