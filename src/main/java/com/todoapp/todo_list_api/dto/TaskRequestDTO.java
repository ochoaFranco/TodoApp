package com.todoapp.todo_list_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO implements Serializable {
    @NotEmpty(message = "Title must not be empty")
    private String title;
    @NotEmpty(message = "Description must not be empty")
    private String description;
    @NotNull(message = "Due_Date must not be null")
    private LocalDate due_date;
    private boolean completed = false;
    @NotNull(message = "categoryId must not be null")
    private Long categoryId;
}
