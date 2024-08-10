package com.todoapp.todo_list_api.controller;

import com.todoapp.todo_list_api.dto.TaskDTO;
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
    public ResponseEntity<TaskDTO> saveTask(@RequestBody TaskDTO taskDTO) {
        taskService.saveTask(taskDTO);
        return new ResponseEntity<>(taskDTO, HttpStatus.CREATED);
    }

    // Read all tasks.
    @GetMapping()
    public ResponseEntity<List<TaskDTO>> getTasks() {
        List<TaskDTO> taskDTOList = taskService.getTasks();
        return new ResponseEntity<>(taskDTOList, HttpStatus.OK);
    }

    // Read one task.
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        Optional<TaskDTO> optionalTask = taskService.getTaskById(id);
        if (optionalTask.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        TaskDTO taskDTO = optionalTask.get();

        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }

    // Update task by its ID.
    @PutMapping("/edit/{id}")
    public ResponseEntity<TaskDTO> editTask(
            @PathVariable Long id,
            @RequestBody TaskDTO taskDTO) {

        Optional<TaskDTO> optionalTaskDTO = taskService.getTaskById(id);
        if (optionalTaskDTO.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        TaskDTO t = taskService.editTask(id, taskDTO);
        return new ResponseEntity<>(t, HttpStatus.OK);
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
