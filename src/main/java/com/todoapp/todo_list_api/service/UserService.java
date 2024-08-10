package com.todoapp.todo_list_api.service;

import com.todoapp.todo_list_api.dto.UserDTO;
import com.todoapp.todo_list_api.model.User;
import com.todoapp.todo_list_api.repository.IUserRepository;
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

    // Create a user.
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = convertDTOToEntity(userDTO);
        user = userRepository.save(user); // save and update the user.
        return convertEntityToDto(user); // return the updated dto.
    }

    // Maps entities to DTOs.
    private UserDTO convertEntityToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    // Maps DTOs to entities.
    private User convertDTOToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    // Get all users.
    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()// convert list to stream
                .map(this::convertEntityToDto) // map each entity to a dto.
                .collect(Collectors.toList()); // collect the dtos into a list.
    }

    // Get one user.
    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(this::convertEntityToDto);
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
        return this.saveUser(this.convertEntityToDto(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
