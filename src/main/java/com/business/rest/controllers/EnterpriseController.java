package com.business.rest.controllers;

import com.business.rest.db.model.Enterprise;
import com.business.rest.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/enterprises")
public class EnterpriseController {
    private final EnterpriseService enterpriseService;

    @Autowired
    public EnterpriseController(EnterpriseService enterpriseService){
        this.enterpriseService = enterpriseService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Enterprise> getAll(){
        return enterpriseService.getAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Enterprise> getEnterprise(@PathVariable Long id) {
        return enterpriseService.getEnterprise(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody Enterprise enterprise) {
        return enterpriseService.save(enterprise);
    }

    @RequestMapping(path = "/save",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveEnterprise(@RequestBody Enterprise enterprise) {
        enterpriseService.saveEnterprise(enterprise);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/delete/{id}")
    public ResponseEntity<?> deleteEnterprise(@PathVariable Long id){
        return enterpriseService.deleteEnterprise(id);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/put")
    public ResponseEntity<?> updateEnterprise(@RequestBody Enterprise enterprise){
        return enterpriseService.updateEnterprise(enterprise);
    }
}
