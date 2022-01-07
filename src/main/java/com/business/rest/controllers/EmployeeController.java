package com.business.rest.controllers;

import com.business.rest.db.model.Employee;
import com.business.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import static com.business.rest.utils.Constants.TOKEN;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAll(@RequestHeader("Authorization") String token){
        if (token.equals(TOKEN)){
            return employeeService.getAll();
        }
        else {
            return Collections.emptyList();
        }
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getClient(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        if (token.equals(TOKEN)){
            return employeeService.getEmployee(id);
        }
        else {
            return null;
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody Employee employee, @RequestHeader("Authorization") String token) {
        if (token.equals(TOKEN)){
            return employeeService.save(employee);
        }
        else {
            return null;
        }
    }

    @RequestMapping(path = "/save/{id}/{id2}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveClient(@RequestBody Employee employee, @PathVariable Long id, @PathVariable Long id2, @RequestHeader("Authorization") String token) {
        if (token.equals(TOKEN)) {
            employeeService.saveEmployee(employee, id, id2);
        }
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id, @RequestHeader("Authorization") String token){
        if (token.equals(TOKEN)){
            return employeeService.deleteEmployee(id);
        }
        else {
            return null;
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/put/{id}/{id2}")
    public ResponseEntity<?> updateClient(@RequestBody Employee employee, @PathVariable Long id, @PathVariable Long id2, @RequestHeader("Authorization") String token){
        if (token.equals(TOKEN)){
            return employeeService.updateEmployee(employee, id, id2);
        }
        else {
            return null;
        }
    }
}
