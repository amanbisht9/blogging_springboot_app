package app.blogging.blogging_application.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.blogging.blogging_application.dto.UserDto;
import app.blogging.blogging_application.services.UserService;
import jakarta.validation.Valid;

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
    public ResponseEntity<UserDto> createUserC(@Valid @RequestBody UserDto user){
        //create user
        UserDto createdUser = this.userService.createUser(user);

        return new ResponseEntity<UserDto>(createdUser, HttpStatus.CREATED);
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getMethodName(@PathVariable("id") Integer id){
        //retrieved user by id
        UserDto user = this.userService.getUserById(id);

        return new ResponseEntity<UserDto>(user, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllMethodName() throws Exception{
        //retrieved user by id
        List<UserDto> users = this.userService.getAllUser();

        return new ResponseEntity<List<UserDto>>(users, HttpStatus.FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> putMethodName(@PathVariable("id") Integer id, @Valid @RequestBody UserDto userDto){
        //update user by id
        UserDto user = this.userService.updateUser(userDto, id);
        
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMethodName(@PathVariable("id") Integer id){
        //update user by id
        this.userService.deleteUserById(id);
    }
    
    
    
}
