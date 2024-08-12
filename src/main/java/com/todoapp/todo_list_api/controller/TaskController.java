package com.todoapp.todo_list_api.controller;

import com.todoapp.todo_list_api.dto.TaskRequestDTO;
import com.todoapp.todo_list_api.dto.TaskResponseDTO;
import com.todoapp.todo_list_api.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private ITaskService taskService;

    // Create a task.
    @PostMapping("/create")
    public ResponseEntity<TaskResponseDTO> saveTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        return new ResponseEntity<>(taskService.saveTask(taskRequestDTO), HttpStatus.CREATED);
    }

    // Read all tasks.
    @GetMapping()
    public ResponseEntity<List<TaskResponseDTO>> getTasks() {
        List<TaskResponseDTO> taskRequestDTOList = taskService.getTasks();
        return new ResponseEntity<>(taskRequestDTOList, HttpStatus.OK);
    }

    // Read one task.
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        Optional<TaskResponseDTO> optionalTask = taskService.getTaskById(id);
        if (optionalTask.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        TaskResponseDTO taskResponseDTO = optionalTask.get();

        return new ResponseEntity<>(taskResponseDTO, HttpStatus.OK);
    }

    // Update task by its ID.
    @PutMapping("/edit/{id}")
    public ResponseEntity<TaskResponseDTO> editTask(
            @PathVariable Long id,
            @RequestBody TaskRequestDTO taskRequestDTO) {
        try {
            return new ResponseEntity<>(taskService.editTask(id, taskRequestDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Delete a category by its ID.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return new ResponseEntity<>("The task was deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("There was an error", HttpStatus.BAD_REQUEST);
        }
    }
}
