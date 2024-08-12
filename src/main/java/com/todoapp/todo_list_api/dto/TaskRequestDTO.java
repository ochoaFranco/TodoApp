package com.todoapp.todo_list_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDTO implements Serializable {
    private String title;
    private String description;
    private LocalDate due_date;
    private boolean completed = false;
    private Long userId;
    private Long categoryId;
}
