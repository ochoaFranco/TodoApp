package com.todoapp.todo_list_api.service;

import com.todoapp.todo_list_api.dto.TaskRequestDTO;
import com.todoapp.todo_list_api.dto.TaskResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ITaskService {
    // creates a task.
    TaskResponseDTO saveTask(TaskRequestDTO taskRequestDTO);
    // Get all uncompleted tasks.
    List<TaskResponseDTO> getUncompletedTasks();
    // Get completed tasks.
    List<TaskResponseDTO> getCompletedTasks();
    // get one task.
    TaskResponseDTO getTaskById(Long id);
    // update a task by its ID.
    TaskResponseDTO editTask(Long id, TaskRequestDTO taskRequestDTO);
    // delete a task.
    void deleteTask(Long id);
    // finish a task
    void finishTask(Long id);
    // mark a task as not finished.
    void notFinished(Long id);
}
