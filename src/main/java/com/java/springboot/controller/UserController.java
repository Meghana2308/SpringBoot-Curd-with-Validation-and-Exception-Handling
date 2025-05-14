package com.java.springboot.controller;

import com.java.springboot.dto.UserDto;
import com.java.springboot.entity.User;
import com.java.springboot.exception.UserNotFoundException;
import com.java.springboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // 1. Save single user
    @PostMapping("/signup")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserDto userDto ){
        return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.CREATED);
    }

    // 2. Save multiple users
    @PostMapping("/saveAll")
    public ResponseEntity<List<User>> saveAllUsers(@RequestBody @Valid List<UserDto> userDtos) {
        return new ResponseEntity<>(userService.saveAllUsers(userDtos), HttpStatus.CREATED);
    }

    // 3. Find all users
    @GetMapping("/allUsers/")
    public ResponseEntity<List<User>> findAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    // 4. Get user by id
    @GetMapping("/usersId/")
    public ResponseEntity<User> getUserById(@PathVariable int id) throws UserNotFoundException {
        return  new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    // 5. Find users by name
    @GetMapping("/name/{name}")
    public ResponseEntity<List<User>> getUsersByName(@PathVariable String name) throws UserNotFoundException {
        return new ResponseEntity<>(userService.findByName(name), HttpStatus.OK);
    }

    // 6. Find user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) throws UserNotFoundException {
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
    }

    // 7. Update user by id
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody @Valid UserDto userDto) {
        try {
            User updatedUser = userService.updateUser(id, userDto);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 8. Delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 9. Delete all users
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.noContent().build();
    }

}
