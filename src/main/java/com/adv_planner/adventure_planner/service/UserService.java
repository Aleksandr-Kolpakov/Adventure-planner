package com.adv_planner.adventure_planner.service;


import com.adv_planner.adventure_planner.dto.UserDTO;
import com.adv_planner.adventure_planner.entity.User;
import com.adv_planner.adventure_planner.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(UserDTO userDto) {
        User newUser = new User();

        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());

        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public Optional<User> updateUser(Long id, UserDTO userDto) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();

            if (userDto.getUsername() != null) {
                updatedUser.setUsername(userDto.getUsername());
            }

            if (userDto.getEmail() != null) {
                updatedUser.setEmail(userDto.getEmail());
            }

            if (userDto.getFirstName() != null) {
                updatedUser.setFirstName(userDto.getFirstName());
            }

            if (userDto.getLastName() != null) {
                updatedUser.setLastName(userDto.getLastName());
            }

            User updUser = userRepository.save(updatedUser);
            return Optional.of(updUser);
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }
}
