package com.business.rest.controllers;

import com.business.rest.db.model.Employee;
import com.business.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAll(){
        return employeeService.getAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getClient(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @RequestMapping(path = "/save/{id}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveClient(@RequestBody Employee employee, @PathVariable Long id) {
        employeeService.saveEmployee(employee, id);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id){
        return employeeService.deleteEmployee(id);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/put/{id}")
    public ResponseEntity<?> updateClient(@RequestBody Employee employee, @PathVariable Long id){
        return employeeService.updateEmployee(employee, id);
    }
}
