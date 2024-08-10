package com.todoapp.todo_list_api.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    private String username;
    private String email;

    public UserDTO() {
    }

    public UserDTO(String email, String username) {
        this.email = email;
        this.username = username;
    }
}
