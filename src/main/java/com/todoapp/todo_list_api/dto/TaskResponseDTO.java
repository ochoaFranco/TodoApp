package com.todoapp.todo_list_api.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO implements Serializable {
    private String title;
    private String description;
    private LocalDate due_date;
    private boolean completed = false;
    private String categoryName;
}
