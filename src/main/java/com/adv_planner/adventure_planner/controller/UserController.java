package com.adv_planner.adventure_planner.controller;

import com.adv_planner.adventure_planner.dto.UserDTO;
import com.adv_planner.adventure_planner.entity.User;
import com.adv_planner.adventure_planner.repository.UserRepository;
import com.adv_planner.adventure_planner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.RequestToViewNameTranslator;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // create user
    @PostMapping("api/users")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDto) {
        User createdUser = userService.createUser(userDto);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    //get all users
    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.getAllUsers();
        return ResponseEntity.ok(list);
    }

    // get user
    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional userOptional = userService.getUserById(id);

        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok((User) userOptional.get());
    }

    // update user
    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDTO userDto) {
       Optional<User> updatedUserOpt = userService.updateUser(id, userDto);

       if (updatedUserOpt.isPresent()) {
           return ResponseEntity.ok(updatedUserOpt.get());
       } else {
           return ResponseEntity.notFound().build();
       }
    }

    // delete user
    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
