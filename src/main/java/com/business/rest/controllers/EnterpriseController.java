package com.business.rest.controllers;

import com.business.rest.db.model.Enterprise;
import com.business.rest.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import static com.business.rest.utils.Constants.TOKEN;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/enterprises")
public class EnterpriseController {
    private final EnterpriseService enterpriseService;

    @Autowired
    public EnterpriseController(EnterpriseService enterpriseService){
        this.enterpriseService = enterpriseService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Enterprise> getAll(@RequestHeader("Authorization") String token){
        if (token.equals(TOKEN)){
            return enterpriseService.getAll();
        }
        else {
            return Collections.emptyList();
        }
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Enterprise> getEnterprise(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        if (token.equals(TOKEN)){
            return enterpriseService.getEnterprise(id);
        }
        else {
            return null;
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody Enterprise enterprise, @RequestHeader("Authorization") String token) {
        if (token.equals(TOKEN)){
            return enterpriseService.save(enterprise);
        }
        else {
            return null;
        }
    }

    @RequestMapping(path = "/save",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveEnterprise(@RequestBody Enterprise enterprise, @RequestHeader("Authorization") String token) {
        if (token.equals(TOKEN)){
            enterpriseService.saveEnterprise(enterprise);
        }
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/delete/{id}")
    public ResponseEntity<?> deleteEnterprise(@PathVariable Long id, @RequestHeader("Authorization") String token){
        if (token.equals(TOKEN)){
            return enterpriseService.deleteEnterprise(id);
        }
        else {
            return null;
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/put")
    public ResponseEntity<?> updateEnterprise(@RequestBody Enterprise enterprise, @RequestHeader("Authorization") String token){
        if (token.equals(TOKEN)){
            return enterpriseService.updateEnterprise(enterprise);
        }
        else {
            return null;
        }
    }
}
