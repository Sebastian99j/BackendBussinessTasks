package com.business.rest.service;

import com.business.rest.db.model.Employee;
import com.business.rest.db.model.Enterprise;
import com.business.rest.repository.EmployeeRepository;
import com.business.rest.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EnterpriseRepository enterpriseRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EnterpriseRepository enterpriseRepository){
        this.employeeRepository = employeeRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public ResponseEntity<Employee> getEmployee(Long id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> save(Employee employee) {
        if(employee.getId() == null) {
            var saved = employeeRepository.save(employee);
            var location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(saved.getId())
                    .toUri();
            return ResponseEntity.created(location).body(employee);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    public void saveEmployee(Employee employee, Long id) {
        Enterprise enterprise = enterpriseRepository.findEnterpriseById(id);
        employee.setEnterprise(enterprise);
        employeeRepository.save(employee);
    }

    public ResponseEntity<?> deleteEmployee(Long id){
        if (employeeRepository.findEmployeeById(id) == null){
            return ResponseEntity.notFound().build();
        }
        else {
            employeeRepository.deleteById(id);
            return ResponseEntity.ok(200);
        }
    }

    public ResponseEntity<?> updateEmployee(Employee employee, Long id){
        if (employeeRepository.findEmployeeById(employee.getId()) != null){
            Enterprise enterprise = enterpriseRepository.findEnterpriseById(id);
            employee.setEnterprise(enterprise);
            employeeRepository.save(employee);
            return ResponseEntity.ok(200);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
