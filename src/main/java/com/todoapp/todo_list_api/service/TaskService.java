package com.todoapp.todo_list_api.service;

import com.todoapp.todo_list_api.dto.TaskDTO;
import com.todoapp.todo_list_api.dto.UserDTO;
import com.todoapp.todo_list_api.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ITaskService {
    @Autowired
    private ITaskRepository taskRepository;

    @Override
    public UserDTO saveTask(TaskDTO taskDTO) {
        return null;
    }

    @Override
    public List<TaskDTO> getTasks() {
        return List.of();
    }

    @Override
    public Optional<TaskDTO> getTaskById(Long id) {
        return Optional.empty();
    }

    @Override
    public UserDTO editTask(Long id, TaskDTO taskDTO) {
        return null;
    }

    @Override
    public void deleteTask(Long id) {

    }
}
