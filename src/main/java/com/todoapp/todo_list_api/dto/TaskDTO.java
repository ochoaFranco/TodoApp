package com.todoapp.todo_list_api.dto;

import com.todoapp.todo_list_api.model.Category;
import com.todoapp.todo_list_api.model.User;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class TaskDTO implements Serializable {
    private String title;
    private String description;
    private LocalDate due_date;
    private boolean completed = false;
    private User user;
    private Category category;

    public TaskDTO() {
    }

    public TaskDTO(Category category, boolean completed, String description, LocalDate due_date, String title, User user) {
        this.category = category;
        this.completed = completed;
        this.description = description;
        this.due_date = due_date;
        this.title = title;
        this.user = user;
    }
}
