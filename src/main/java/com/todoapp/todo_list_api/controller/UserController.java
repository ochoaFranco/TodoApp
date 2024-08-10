package com.todoapp.todo_list_api.controller;

import com.todoapp.todo_list_api.dto.UserDTO;
import com.todoapp.todo_list_api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    // Create a user.
    @PostMapping("/create")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        userService.saveUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    // Read all users.
    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> userDTOList = userService.getUsers();
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    // Read one user.
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<UserDTO> optionalCategory = userService.getUserById(id);
        if (optionalCategory.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        UserDTO userDTO = optionalCategory.get();

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    // Update user by its ID.
    @PutMapping("/edit/{id}")
    public ResponseEntity<UserDTO> editUser(
            @PathVariable Long id,
            @RequestBody UserDTO userDTO) {

        Optional<UserDTO> optionalUserDTO = userService.getUserById(id);
        if (optionalUserDTO.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        UserDTO u = userService.editUser(id, userDTO);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }


    // Delete a category by its ID.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("The client was deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("There was an error", HttpStatus.BAD_REQUEST);
        }
    }
}
