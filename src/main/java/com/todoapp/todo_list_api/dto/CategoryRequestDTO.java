package com.todoapp.todo_list_api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
@Data
public class CategoryRequestDTO implements Serializable {
    @NotEmpty
    private String name;
}
