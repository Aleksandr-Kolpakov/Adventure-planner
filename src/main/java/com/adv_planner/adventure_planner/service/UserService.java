package com.adv_planner.adventure_planner.service;


import com.adv_planner.adventure_planner.dto.UserDTO;
import com.adv_planner.adventure_planner.entity.User;
import com.adv_planner.adventure_planner.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


// create user
    public User createUser(UserDTO userDto) {
        User user = new User();

            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());

        return userRepository.save(user);
    }


// get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


// get user by id
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found: " + id));
    }


// update user
    public User updateUser(Long id, UserDTO userDto) {
        User user = getUserById(id);

        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        return userRepository.save(user);
    }


    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found: " + id);
        }

        userRepository.deleteById(id);
    }
}
