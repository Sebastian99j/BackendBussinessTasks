package com.business.rest.controllers;

import com.business.rest.db.model.User;
import com.business.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import static com.business.rest.utils.Constants.TOKEN;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll(@RequestHeader("Authorization") String token){
        if (token.equals(TOKEN)){
            return userService.getAll();
        }
        else {
            return Collections.emptyList();
        }
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getClient(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        if (token.equals(TOKEN)){
            return userService.getUser(id);
        }
        else {
            return null;
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody User user, @RequestHeader("Authorization") String token) {
        if (token.equals(TOKEN)){
            return userService.save(user);
        }
        else {
            return null;
        }
    }

    @RequestMapping(path = "/save",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveClient(@RequestBody User user, @RequestHeader("Authorization") String token) {
        if (token.equals(TOKEN)){
            userService.saveUser(user);
        }
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id, @RequestHeader("Authorization") String token){
        if (token.equals(TOKEN)){
            return userService.deleteUser(id);
        }
        else {
            return null;
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/put")
    public ResponseEntity<?> updateClient(@RequestBody User user, @RequestHeader("Authorization") String token){
        if (token.equals(TOKEN)){
            return userService.updateUser(user);
        }
        else {
            return null;
        }
    }
}
