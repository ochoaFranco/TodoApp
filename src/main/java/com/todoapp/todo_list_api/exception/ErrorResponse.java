package com.todoapp.todo_list_api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private String details;
}
