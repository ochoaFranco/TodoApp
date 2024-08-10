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

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public UserDTO editUser(Long id, UserDTO user) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
