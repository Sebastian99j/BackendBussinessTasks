package com.business.rest.controllers;

import com.business.rest.db.model.Response;
import com.business.rest.db.model.User;
import com.business.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.business.rest.utils.Constants.TOKEN;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/validate")
public class ValidateController {
    private final UserService userService;

    @Autowired
    public ValidateController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> validate(@RequestBody User user, @RequestHeader("Authorization") String token) {
        if (token.equals(TOKEN)){
            return userService.validateUser(user);
        }
        else {
            return ResponseEntity.ok(new Response("reject"));
        }
    }
}
