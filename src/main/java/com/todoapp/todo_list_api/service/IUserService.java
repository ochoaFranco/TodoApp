package com.todoapp.todo_list_api.service;

import com.todoapp.todo_list_api.dto.UserDTO;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    // creates a user.
    UserDTO saveUser(UserDTO user);
    // Get all users.
    List<UserDTO> getUsers();
    // get one user.
    Optional<UserDTO> getUserById(Long id);
    // update a user.
    UserDTO editUser(Long id, UserDTO user);
    // delete a user.
    void deleteUser(Long id);
}
