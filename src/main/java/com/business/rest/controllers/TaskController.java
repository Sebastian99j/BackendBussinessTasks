package com.business.rest.controllers;

import com.business.rest.db.model.Task;
import com.business.rest.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import static com.business.rest.utils.Constants.TOKEN;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getAll(@RequestHeader("Authorization") String token){
        if (token.equals(TOKEN)){
            return taskService.getAll();
        }
        else {
            return Collections.emptyList();
        }
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> getTask(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        if (token.equals(TOKEN)){
            return taskService.getTask(id);
        }
        else {
            return null;
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody Task task, @RequestHeader("Authorization") String token) {
        if (token.equals(TOKEN)){
            return taskService.save(task);
        }
        else {
            return null;
        }
    }

    @RequestMapping(path = "/save/{id}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveTask(@RequestBody Task task, @PathVariable Long id, @RequestHeader("Authorization") String token) {
        if (token.equals(TOKEN)){
            taskService.saveTask(task, id);
        }
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id, @RequestHeader("Authorization") String token){
        if (token.equals(TOKEN)){
            return taskService.deleteTask(id);
        }
        else {
            return null;
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/put/{id}")
    public ResponseEntity<?> updateTask(@RequestBody Task task, @PathVariable Long id, @RequestHeader("Authorization") String token){
        if (token.equals(TOKEN)){
            return taskService.updateTask(task, id);
        }
        else {
            return null;
        }
    }
}
