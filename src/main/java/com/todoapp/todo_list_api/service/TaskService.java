package com.todoapp.todo_list_api.service;

import com.todoapp.todo_list_api.dto.TaskDTO;
import com.todoapp.todo_list_api.dto.UserDTO;
import com.todoapp.todo_list_api.model.Task;
import com.todoapp.todo_list_api.model.User;
import com.todoapp.todo_list_api.repository.ITaskRepository;
import com.todoapp.todo_list_api.utils.Util;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService implements ITaskService {
    @Autowired
    private ITaskRepository taskRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Util util;

    // Create a task.
    @Override
    public TaskDTO saveTask(TaskDTO taskDTO) {
        Task task = util.convert(taskDTO, Task.class);
        task = taskRepository.save(task); // save and update the task.
        return util.convert(task, TaskDTO.class); // return the updated dto.
    }

    // Get all tasks.
    @Override
    public List<TaskDTO> getTasks() {
        return taskRepository.findAll()
                .stream()// convert list to stream
                .map(task -> util.convert(task, TaskDTO.class)) // map each entity to a dto.
                .collect(Collectors.toList()); // collect the dtos into a list.
    }

    // Get task by id.
    @Override
    public Optional<TaskDTO> getTaskById(Long id) {
        return taskRepository.findById(id).map(task -> util.convert(task, TaskDTO.class));
    }

    // Edit a task by its ID.
    @Override
    public TaskDTO editTask(Long id, TaskDTO taskDTO) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task task = optionalTask.get();
        if (taskDTO.getCategory() != null)
            task.setCategory(taskDTO.getCategory());

        if (taskDTO.getUser() != null)
            task.setUser(taskDTO.getUser());

        if (task.getTitle() != null)
            task.setTitle(task.getTitle());

        if (task.getDescription() != null)
            task.setDescription(task.getDescription());

        return util.convert(taskRepository.save(task), TaskDTO.class);
    }

    // Delete one task.
    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
