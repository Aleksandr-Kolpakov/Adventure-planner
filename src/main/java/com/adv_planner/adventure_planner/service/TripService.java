package com.adv_planner.adventure_planner.service;

import com.adv_planner.adventure_planner.dto.TripDTO;
import com.adv_planner.adventure_planner.entity.Trip;
import com.adv_planner.adventure_planner.entity.User;
import com.adv_planner.adventure_planner.repository.TripPointRepository;
import com.adv_planner.adventure_planner.repository.TripRepository;
import com.adv_planner.adventure_planner.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    private final TripRepository tripRepository;
    private final UserRepository userRepository;
    private final TripPointRepository tripPointRepository;


    @Autowired
    public TripService(TripRepository tripRepository, UserRepository userRepository,
                       TripPointRepository tripPointRepository) {
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
        this.tripPointRepository = tripPointRepository;
    }

    @Transactional
    public Trip createTrip(TripDTO tripDto) {
        Optional<User> creator = userRepository.findById(tripDto.getCreatorId());
        Trip trip = new Trip();

        trip.setTitle(tripDto.getTitle());
        trip.setDescription(tripDto.getDescription());
        trip.setStartDate(tripDto.getStartDate());
        trip.setEndDate(tripDto.getEndDate());
        trip.setCreatedAt(tripDto.getCreatedAt());

        creator.ifPresent(trip::setCreator);

        return tripRepository.save(trip);
    }

    public Optional<Trip> getTripById(Long id) {
        return tripRepository.findById(id);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @Transactional
    public Optional<Trip> updateTrip(Long id, TripDTO tripDto) {
        Optional<Trip> existingTrip = tripRepository.findById(id);

        if (existingTrip.isPresent()) {
            Trip updatedTrip = existingTrip.get();

            if (tripDto.getTitle() != null) {
                updatedTrip.setTitle(tripDto.getTitle());
            }

            if (tripDto.getDescription() != null) {
                updatedTrip.setDescription(tripDto.getDescription());
            }

            if (tripDto.getStartDate() != null) {
                updatedTrip.setStartDate(tripDto.getStartDate());
            }

            if (tripDto.getEndDate() != null) {
                updatedTrip.setEndDate(tripDto.getEndDate());
            }

            Trip updUser = tripRepository.save(updatedTrip);
            return Optional.of(updatedTrip);
        } else {
            return Optional.empty();
        }
    }

    public void deleteTrip(Long id) {
        if (tripRepository.existsById(id)) {
            tripRepository.deleteById(id);
        } else {
            throw new RuntimeException("Trip not found with ID: " + id);
        }
    }
}
