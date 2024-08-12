package com.todoapp.todo_list_api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
}
