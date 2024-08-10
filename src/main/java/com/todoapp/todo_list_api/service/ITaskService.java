package com.todoapp.todo_list_api.service;

import com.todoapp.todo_list_api.dto.TaskDTO;
import com.todoapp.todo_list_api.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface ITaskService {
    // creates a task.
    UserDTO saveTask(TaskDTO taskDTO);
    // Get all tasks.
    List<TaskDTO> getTasks();
    // get one task.
    Optional<TaskDTO> getTaskById(Long id);
    // update a task by its ID.
    UserDTO editTask(Long id, TaskDTO taskDTO);
    // delete a task.
    void deleteTask(Long id);
}
