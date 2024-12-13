package app.blogging.blogging_application.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.blogging.blogging_application.dto.UserDto;
import app.blogging.blogging_application.entity.User;
import app.blogging.blogging_application.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUserC(@RequestBody UserDto user) throws Exception {
        //create user
        User createdUser = userService.createUser(user);

        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
        
    }

    @GetMapping("/{id}")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    // @PutMapping("path/{id}")
    // public String putMethodName(@PathVariable String id, @RequestBody String entity) {
    //     //TODO: process PUT request
        
    //     return entity;
    // }

    // @DeleteMapping("/delete")
    
    
    
}
