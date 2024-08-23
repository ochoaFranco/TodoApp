package com.todoapp.todo_list_api.exception;

public class DuplicateCategoryNameException extends RuntimeException {
    public DuplicateCategoryNameException(String message) {
        super(message);
    }
}
