package app.blogging.blogging_application.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.blogging.blogging_application.dto.UserDto;
import app.blogging.blogging_application.entity.User;
import app.blogging.blogging_application.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUserC(@RequestBody UserDto user) throws Exception {
        //create user
        User createdUser = this.userService.createUser(user);

        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getMethodName(@PathVariable("id") Integer id){
        //retrieved user by id
        User user = this.userService.getUserById(id);

        return new ResponseEntity<User>(user, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllMethodName() throws Exception{
        //retrieved user by id
        List<User> users = this.userService.getAllUser();

        return new ResponseEntity<List<User>>(users, HttpStatus.FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> putMethodName(@PathVariable("id") Integer id, @RequestBody UserDto userDto){
        //update user by id
        User user = this.userService.updateUser(userDto, id);
        
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteMethodName(@PathVariable("id") Integer id){
        //update user by id
        this.userService.deleteUserById(id);;
        
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
    
    
    
}
