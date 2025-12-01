package com.adv_planner.adventure_planner.service;

import com.adv_planner.adventure_planner.dto.UserDTO;
import com.adv_planner.adventure_planner.entity.Trip;
import com.adv_planner.adventure_planner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService {
    private final UserRepository userRepository;

    @Autowired
    public TripService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Trip createTrip(UserDTO userDTO) {
        return null;
    }
}
