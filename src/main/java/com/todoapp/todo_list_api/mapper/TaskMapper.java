package com.todoapp.todo_list_api.mapper;

import com.todoapp.todo_list_api.dto.TaskRequestDTO;
import com.todoapp.todo_list_api.dto.TaskResponseDTO;
import com.todoapp.todo_list_api.model.Category;
import com.todoapp.todo_list_api.model.Task;
import com.todoapp.todo_list_api.model.User;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toTask(TaskRequestDTO taskRequestDTO) {
        Task task = new Task();
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setDue_date(taskRequestDTO.getDue_date());
        Category category = new Category();
        category.setCategoryId(taskRequestDTO.getCategoryId());
        task.setCategory(category);
        task.setCompleted(taskRequestDTO.isCompleted());
        User user = new User();
        user.setUserId(taskRequestDTO.getUserId());
        task.setUser(user);
        return task;
    }

    public TaskResponseDTO toResponseDTO(Task task) {
        return new TaskResponseDTO(
                task.getTaskId(),
                task.getTitle(),
                task.getDescription(),
                task.getDue_date(),
                task.isCompleted(),
                task.getCategory().getName()
        );
    }
}
