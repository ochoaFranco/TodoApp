package com.todoapp.todo_list_api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO implements Serializable {
    private Long id;
    private String title;
    private String description;
    private LocalDate due_date;
    private boolean completed = false;
    private String categoryName;
}
