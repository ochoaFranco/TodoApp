package com.todoapp.todo_list_api.service;

import com.todoapp.todo_list_api.dto.TaskDTO;
import com.todoapp.todo_list_api.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface ITaskService {
    // creates a task.
    TaskDTO saveTask(TaskDTO taskDTO);
    // Get all tasks.
    List<TaskDTO> getTasks();
    // get one task.
    Optional<TaskDTO> getTaskById(Long id);
    // update a task by its ID.
    TaskDTO editTask(Long id, TaskDTO taskDTO);
    // delete a task.
    void deleteTask(Long id);
}
