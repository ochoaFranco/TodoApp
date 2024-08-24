package com.todoapp.todo_list_api.controller;

import com.todoapp.todo_list_api.dto.TaskRequestDTO;
import com.todoapp.todo_list_api.dto.TaskResponseDTO;
import com.todoapp.todo_list_api.exception.ErrorResponse;
import com.todoapp.todo_list_api.service.ITaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Create a task", description = "A new task is created")
    @ApiResponse(responseCode = "201", description = "Category has been deleted successfully!")
    @ApiResponse(responseCode = "400", description = "An error has occurred")
    // Create a task.
    @PostMapping("/create")
    public ResponseEntity<?> saveTask(
            @Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        try {
            return new ResponseEntity<>(taskService.saveTask(taskRequestDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    "There was an error", "the task could not be created"
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get all uncompleted tasks", description = "Display all tasks that have not been completed yet.")
    @ApiResponse(responseCode = "200", description = "All tasks have been retrieved successfully!")
    @ApiResponse(responseCode = "400", description = "An error has occurred")
    // Read all uncompleted tasks.
    @GetMapping("/uncompleted")
    public ResponseEntity<?> getUncompletedTasks() {
        try {
            List<TaskResponseDTO> taskRequestDTOList = taskService.getUncompletedTasks();
            return new ResponseEntity<>(taskRequestDTOList, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    "There was an error", "the task could not be created"
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get all completed tasks", description = "Display all tasks that have been completed so far.")
    @ApiResponse(responseCode = "200", description = "All tasks have been retrieved successfully!")
    @ApiResponse(responseCode = "400", description = "An error has occurred")
    // Read all completed tasks.
    @GetMapping("/completed")
    public ResponseEntity<?> getCompletedTasks() {
        try {
            List<TaskResponseDTO> taskRequestDTOList = taskService.getCompletedTasks();
            return new ResponseEntity<>(taskRequestDTOList, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    "There was an error", "the task could not be created"
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get a task by id", description = "Retrieve a task by its ID.")
    @ApiResponse(responseCode = "200", description = "task has been retrieved successfully!")
    @ApiResponse(responseCode = "404", description = "An error has occurred")
    // Read one task.
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        try {
            TaskResponseDTO taskResponseDTO = taskService.getTaskById(id);
            return new ResponseEntity<>(taskResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "edit a task by id", description = "Edit a task by its ID.")
    @ApiResponse(responseCode = "200", description = "task has been modified successfully!")
    @ApiResponse(responseCode = "400", description = "An error has occurred")
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

    @Operation(summary = "Mark a task as completed", description = "Endpoint that allows a task to be marked as completed.")
    @ApiResponse(responseCode = "200", description = "task has been modified successfully!")
    @ApiResponse(responseCode = "400", description = "An error has occurred")
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

    @Operation(summary = "Mark a task as uncompleted", description = "Endpoint that allows a task to be marked as uncompleted.")
    @ApiResponse(responseCode = "200", description = "task has been modified successfully!")
    @ApiResponse(responseCode = "400", description = "An error has occurred")
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

    @Operation(summary = "Delete a task by its ID", description = "Endpoint that allows a task to be deleted by its ID.")
    @ApiResponse(responseCode = "200", description = "task has been deleted successfully!")
    @ApiResponse(responseCode = "400", description = "An error has occurred")
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
