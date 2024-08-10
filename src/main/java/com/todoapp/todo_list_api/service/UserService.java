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
        return userDTO;
    }

    private User converDTOToEntity(UserDTO userDTO) {
        User user = new User();
        user = modelMapper.map(userDTO, User.class);
        return user;
    }

    // Get all users.
    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    // Maps entities to dtos.
    private UserDTO convertEntityToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
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
