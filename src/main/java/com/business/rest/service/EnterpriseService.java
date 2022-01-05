package com.business.rest.service;

import com.business.rest.db.model.Enterprise;
import com.business.rest.db.model.User;
import com.business.rest.repository.EnterpriseRepository;
import com.business.rest.repository.UserRepository;
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
public class EnterpriseService {
    private final EnterpriseRepository enterpriseRepository;
    private final UserRepository userRepository;

    @Autowired
    public EnterpriseService(EnterpriseRepository enterpriseRepository, UserRepository userRepository){
        this.enterpriseRepository = enterpriseRepository;
        this.userRepository = userRepository;
    }

    public List<Enterprise> getAll(){
        return enterpriseRepository.findAll();
    }

    public ResponseEntity<Enterprise> getEnterprise(Long id) {
        return enterpriseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> save(Enterprise enterprise) {
        if(enterprise.getId() == null) {
            var saved = enterpriseRepository.save(enterprise);
            var location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(saved.getId())
                    .toUri();
            return ResponseEntity.created(location).body(enterprise);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    public void saveEnterprise(Enterprise enterprise, Long id) {
        User user = userRepository.findUserById(id);
        enterprise.setUser(user);
        enterpriseRepository.save(enterprise);
    }

    public ResponseEntity<?> deleteEnterprise(Long id){
        if (enterpriseRepository.findEnterpriseById(id) == null){
            return ResponseEntity.notFound().build();
        }
        else {
            enterpriseRepository.deleteById(id);
            return ResponseEntity.ok(200);
        }
    }

    public ResponseEntity<?> updateEnterprise(Enterprise enterprise, Long id){
        if (enterpriseRepository.findEnterpriseById(enterprise.getId()) != null){
            User user = userRepository.findUserById(id);
            enterprise.setUser(user);
            enterpriseRepository.save(enterprise);
            return ResponseEntity.ok(200);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
