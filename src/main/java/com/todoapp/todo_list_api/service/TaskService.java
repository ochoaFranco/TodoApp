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
        Task task = new Task();
        // Setting attributes.
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
        task = taskRepository.save(task);

        return new TaskResponseDTO(
                task.getTitle(),
                task.getDescription(),
                task.getDue_date(),
                task.isCompleted()
        );
    }

    // Get all tasks.
    @Override
    public List<TaskRequestDTO> getTasks() {
        return taskRepository.findAll()
                .stream()// convert list to stream
                .map(task -> util.convert(task, TaskRequestDTO.class)) // map each entity to a dto.
                .collect(Collectors.toList()); // collect the dtos into a list.
    }

    // Get task by id.
    @Override
    public Optional<TaskRequestDTO> getTaskById(Long id) {
        return taskRepository.findById(id).map(task -> util.convert(task, TaskRequestDTO.class));
    }

    // Edit a task by its ID.
    @Override
    public TaskRequestDTO editTask(Long id, TaskRequestDTO taskRequestDTO) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        // fetching the task by its ID.
        if (optionalTask.isEmpty())
            throw new RuntimeException("Task not found");

        Task task = optionalTask.get();

        if (taskRequestDTO.getCategoryId() != null) {
            Category category = categoryRepository
                    .findById(taskRequestDTO.getCategoryId())
                    .orElseThrow(()-> new RuntimeException("Category Not found"));
            task.setCategory(category);
        }

        // Set the user if the userId is provided
        if (taskRequestDTO.getUserId() != null) {
            User user = userRepository.findById(taskRequestDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            task.setUser(user);
        }

        if (task.getTitle() != null)
            task.setTitle(task.getTitle());

        if (task.getDescription() != null)
            task.setDescription(task.getDescription());

        return util.convert(taskRepository.save(task), TaskRequestDTO.class);
    }

    // Delete one task.
    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
