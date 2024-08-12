package com.todoapp.todo_list_api.service;

import com.todoapp.todo_list_api.dto.TaskRequestDTO;
import com.todoapp.todo_list_api.dto.TaskResponseDTO;
import com.todoapp.todo_list_api.model.Category;
import com.todoapp.todo_list_api.model.Task;
import com.todoapp.todo_list_api.model.User;
import com.todoapp.todo_list_api.repository.ICategoryRepository;
import com.todoapp.todo_list_api.repository.ITaskRepository;
import com.todoapp.todo_list_api.repository.IUserRepository;
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
    private ICategoryRepository categoryRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Util util;

    // Create a task.
    @Override
    public TaskResponseDTO saveTask(TaskRequestDTO taskRequestDTO) {
        Task task = getTask(taskRequestDTO);
        task = taskRepository.save(task);
        Optional <Category> optionalCategory = categoryRepository.findById(taskRequestDTO.getCategoryId());
        String categoryName = " ";
        if (optionalCategory.isPresent())
            categoryName = optionalCategory.get().getName();
        System.out.println("NAME: " + categoryName);
        return new TaskResponseDTO(
                task.getTitle(),
                task.getDescription(),
                task.getDue_date(),
                task.isCompleted(),
                categoryName
        );
    }

    private Task getTask(TaskRequestDTO taskRequestDTO) {
        Task task = new Task();
        setAttributes(taskRequestDTO, task);
        return task;
    }

    // Get all tasks.
    @Override
    public List<TaskResponseDTO> getTasks() {
        return taskRepository.findAll()
                .stream()// convert list to stream
                .map(task -> util.convert(task, TaskResponseDTO.class)) // map each entity to a dto.
                .collect(Collectors.toList()); // collect the dtos into a list.
    }

    // Get task by id.
    @Override
    public Optional<TaskResponseDTO> getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(task -> util.convert(task, TaskResponseDTO.class));
    }

    // Edit a task by its ID.
    @Override
    public TaskResponseDTO editTask(Long id, TaskRequestDTO taskRequestDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Task not found"));
        setAttributes(taskRequestDTO, task);
        task = taskRepository.save(task);
        Optional <Category> optionalCategory = categoryRepository.findById(taskRequestDTO.getCategoryId());
        String categoryName = " ";

        if (optionalCategory.isPresent())
            categoryName = optionalCategory.get().getName();

        return new TaskResponseDTO(
                task.getTitle(),
                task.getDescription(),
                task.getDue_date(),
                task.isCompleted(),
                categoryName
        );
    }

    // Take a task and set its attributes based on a given request.
    private void setAttributes(TaskRequestDTO taskRequestDTO, Task task) {
        // Set attributes.
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setDue_date(taskRequestDTO.getDue_date());
        task.setCompleted(taskRequestDTO.isCompleted());
        User user = new User();
        user.setUserId(taskRequestDTO.getUserId());
        Category category = new Category();
        category.setCategoryId(taskRequestDTO.getCategoryId());
        task.setUser(user);
        task.setCategory(category);
    }

    // Delete one task.
    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
