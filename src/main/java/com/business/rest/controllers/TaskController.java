package com.business.rest.controllers;

import com.business.rest.db.model.Task;
import com.business.rest.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getAll(){
        return taskService.getAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody Task task) {
        return taskService.save(task);
    }

    @RequestMapping(path = "/save",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveTask(@RequestBody Task task) {
        taskService.saveTask(task);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        return taskService.deleteTask(id);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/put")
    public ResponseEntity<?> updateTask(@RequestBody Task task){
        return taskService.updateTask(task);
    }
}
