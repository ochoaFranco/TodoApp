package com.todoapp.todo_list_api.service;

import com.todoapp.todo_list_api.dto.TaskRequestDTO;
import com.todoapp.todo_list_api.dto.TaskResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ITaskService {
    // creates a task.
    TaskResponseDTO saveTask(TaskRequestDTO taskRequestDTO);
    // Get all tasks.
    List<TaskRequestDTO> getTasks();
    // get one task.
    Optional<TaskRequestDTO> getTaskById(Long id);
    // update a task by its ID.
    TaskRequestDTO editTask(Long id, TaskRequestDTO taskRequestDTO);
    // delete a task.
    void deleteTask(Long id);
}
