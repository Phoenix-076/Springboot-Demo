package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.service.UserService;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController // Makes this class handle web requests
@RequestMapping("/api/users") // Base path for all endpoints in this controller
public class UserController {

    @Autowired
    private UserService userService;

    // Get all users -READ
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get single user by ID -READ
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new user -CREATE
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // Update an existing user -UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updateUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updateUser);
    }

    // Delete a user -DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok("User deleted successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
