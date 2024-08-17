package com.todoapp.todo_list_api.service;

import com.todoapp.todo_list_api.dto.TaskRequestDTO;
import com.todoapp.todo_list_api.dto.TaskResponseDTO;
import com.todoapp.todo_list_api.mapper.TaskMapper;
import com.todoapp.todo_list_api.model.Category;
import com.todoapp.todo_list_api.model.Task;
import com.todoapp.todo_list_api.repository.ICategoryRepository;
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
    private ICategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Util util;
    @Autowired
    private TaskMapper taskMapper;

    // Create a task.
    @Override
    public TaskResponseDTO saveTask(TaskRequestDTO taskRequestDTO) {
        Task task = taskMapper.toTask(taskRequestDTO);
        taskRepository.save(task);
        return taskMapper.toResponseDTO(task);
    }
    // Get all uncompleted tasks.
    @Override
    public List<TaskResponseDTO> getUncompletedTasks() {
        return taskRepository.findAll()
                .stream()// convert list to stream
                .filter(task -> !task.isCompleted())
                .map(task -> util.convert(task, TaskResponseDTO.class)) // map each entity to a dto.
                .collect(Collectors.toList()); // collect the dtos into a list.
    }

    // Get all completed tasks.
    @Override
    public List<TaskResponseDTO> getCompletedTasks() {
        return taskRepository.findAll()
                .stream()// convert list to stream
                .filter(Task::isCompleted)
                .map(task -> util.convert(task, TaskResponseDTO.class)) // map each entity to a dto.
                .collect(Collectors.toList()); // collect the dtos into a list.
    }

    // Get task by id.
    @Override
    public TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return taskMapper.toResponseDTO(task);
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
                task.getTaskId(),
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
        Category category = new Category();
        category.setCategoryId(taskRequestDTO.getCategoryId());
        task.setCategory(category);
    }

    // Delete one task.
    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // Mark a task as finished.
    @Override
    public void finishTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task not found"));
        task.setCompleted(true);
        taskRepository.save(task);
    }

    // Mark a task as not finished.
    @Override
    public void notFinished (Long id) {
        Task task = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task not found"));
        task.setCompleted(false);
        taskRepository.save(task);
    }
}
