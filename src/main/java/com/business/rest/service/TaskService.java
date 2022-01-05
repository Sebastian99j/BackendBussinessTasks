package com.business.rest.service;

import com.business.rest.db.model.Employee;
import com.business.rest.db.model.Task;
import com.business.rest.repository.EmployeeRepository;
import com.business.rest.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, EmployeeRepository employeeRepository){
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Task> getAll(){
        return taskRepository.findAll();
    }

    public ResponseEntity<Task> getTask(Long id) {
        return taskRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> save(Task task) {
        if(task.getId() == null) {
            var saved = taskRepository.save(task);
            var location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(saved.getId())
                    .toUri();
            return ResponseEntity.created(location).body(task);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    public void saveTask(Task task, Long id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        task.setEmployee(employee);
        taskRepository.save(task);
    }

    public ResponseEntity<?> deleteTask(Long id){
        if (taskRepository.findTaskById(id) == null){
            return ResponseEntity.notFound().build();
        }
        else {
            taskRepository.deleteById(id);
            return ResponseEntity.ok(200);
        }
    }

    public ResponseEntity<?> updateTask(Task task, Long id){
        if (taskRepository.findTaskById(task.getId()) != null){
            Employee employee = employeeRepository.findEmployeeById(id);
            task.setEmployee(employee);
            taskRepository.save(task);
            return ResponseEntity.ok(200);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
