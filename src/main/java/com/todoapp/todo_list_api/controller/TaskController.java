package com.todoapp.todo_list_api.controller;

import com.todoapp.todo_list_api.dto.TaskRequestDTO;
import com.todoapp.todo_list_api.dto.TaskResponseDTO;
import com.todoapp.todo_list_api.service.ITaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private ITaskService taskService;

    // Create a task.
    @PostMapping("/create")
    public ResponseEntity<TaskResponseDTO> saveTask(
            @Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        return new ResponseEntity<>(taskService.saveTask(taskRequestDTO), HttpStatus.CREATED);
    }

    // Read all uncompleted tasks.
    @GetMapping("/uncompleted")
    public ResponseEntity<List<TaskResponseDTO>> getUncompletedTasks() {
        List<TaskResponseDTO> taskRequestDTOList = taskService.getUncompletedTasks();
        return new ResponseEntity<>(taskRequestDTOList, HttpStatus.OK);
    }

    // Read all completed tasks.
    @GetMapping("/completed")
    public ResponseEntity<List<TaskResponseDTO>> getCompletedTasks() {
        List<TaskResponseDTO> taskRequestDTOList = taskService.getCompletedTasks();
        return new ResponseEntity<>(taskRequestDTOList, HttpStatus.OK);
    }

    // Read one task.
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        try {
            TaskResponseDTO taskResponseDTO = taskService.getTaskById(id);
            return new ResponseEntity<>(taskResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update task by its ID.
    @PutMapping("/edit/{id}")
    public ResponseEntity<TaskResponseDTO> editTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        try {
            return new ResponseEntity<>(taskService.editTask(id, taskRequestDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Mark a task as completed.
    @PatchMapping("/{id}/completed")
    public ResponseEntity<String> finishTask(@PathVariable Long id) {
        try {
            taskService.finishTask(id);
            return new ResponseEntity<>("The task was marked as completed", HttpStatus.OK);
        } catch (Exception e ) {
            return new ResponseEntity<>("There was an error", HttpStatus.BAD_REQUEST);
        }
    }

    // Mark a task as unCompleted.
    @PatchMapping("/{id}/uncompleted")
    public ResponseEntity<String> notFinishedTask(@PathVariable Long id) {
        try {
            taskService.notFinished(id);
            return new ResponseEntity<>("The task was marked as uncompleted", HttpStatus.OK);
        } catch (Exception e ) {
            return new ResponseEntity<>("There was an error", HttpStatus.BAD_REQUEST);
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
