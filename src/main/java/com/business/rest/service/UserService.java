package com.business.rest.service;

import com.business.rest.db.model.Response;
import com.business.rest.db.model.User;
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
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public ResponseEntity<User> getUser(Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> save(User user) {
        if(user.getId() == null) {
            var saved = userRepository.save(user);
            var location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(saved.getId())
                    .toUri();
            return ResponseEntity.created(location).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public ResponseEntity<?> deleteUser(Long id){
        if (userRepository.findUserById(id) == null){
            return ResponseEntity.notFound().build();
        }
        else {
            userRepository.deleteById(id);
            return ResponseEntity.ok(200);
        }
    }

    public ResponseEntity<?> updateUser(User user){
        if (userRepository.findUserById(user.getId()) != null){
            userRepository.save(user);
            return ResponseEntity.ok(200);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Response> validateUser(User user){
        var users = userRepository.findAll();
        for (User item: users){
            if (user.getName().equals(item.getName()) && user.getPassword().equals(item.getPassword())){
                return ResponseEntity.ok(new Response("ok"));
            }
        }
        return ResponseEntity.ok(new Response("reject"));
    }
}
