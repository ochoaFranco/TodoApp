package com.todoapp.todo_list_api.service;

import com.todoapp.todo_list_api.dto.UserDTO;
import com.todoapp.todo_list_api.model.User;
import com.todoapp.todo_list_api.repository.IUserRepository;
import com.todoapp.todo_list_api.utils.Util;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Util util;

    // Create a user.
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = util.convert(userDTO, User.class);
        user = userRepository.save(user); // save and update the user.
        return util.convert(user, UserDTO.class); // return the updated dto.
    }

    // Get all users.
    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()// convert list to stream
                .map(user -> util.convert(user, UserDTO.class)) // map each entity to a dto.
                .collect(Collectors.toList()); // collect the dtos into a list.
    }

    // Get one user.
    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(user -> util.convert(user, UserDTO.class));
    }

    // Edit a user by its ID.
    @Override
    public UserDTO editUser(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        if (userDTO.getEmail() != null)
            user.setEmail(userDTO.getEmail());
        if (userDTO.getUsername() != null)
            user.setUsername(userDTO.getUsername());
        return util.convert(userRepository.save(user), UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
