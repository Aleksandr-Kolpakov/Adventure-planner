package com.adv_planner.adventure_planner.service;

import com.adv_planner.adventure_planner.dto.TripDTO;
import com.adv_planner.adventure_planner.entity.Trip;
import com.adv_planner.adventure_planner.entity.User;
import com.adv_planner.adventure_planner.repository.TripRepository;
import com.adv_planner.adventure_planner.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TripService {
    private final TripRepository tripRepository;

    private final UserService userService;

    @Autowired
    public TripService(TripRepository tripRepository, UserService userService) {
        this.tripRepository = tripRepository;
        this.userService = userService;
    }


// create trip
    public Trip createTrip(TripDTO tripDto) {
        User creator = userService.getUserById(tripDto.getCreatorId());
        Trip trip = new Trip();

        trip.setTitle(tripDto.getTitle());
        trip.setDescription(tripDto.getDescription());
        trip.setStartDate(tripDto.getStartDate());
        trip.setEndDate(tripDto.getEndDate());
        trip.setCreator(creator);

        return tripRepository.save(trip);
    }


// get trip by id
    public Trip getTripById(Long id) {
        return tripRepository.findById(id).orElseThrow(() -> new RuntimeException("Trip not found exception: " + id));
    }


// get all trips
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }


// update trip
    public Trip updateTrip(Long id, TripDTO tripDto) {
        Trip trip = this.getTripById(id);

        trip.setTitle(tripDto.getTitle());
        trip.setDescription(tripDto.getDescription());
        trip.setStartDate(tripDto.getStartDate());
        trip.setEndDate(tripDto.getEndDate());

        return tripRepository.save(trip);
    }


// delete trip
    public void deleteTrip(Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new RuntimeException("Trip not found: " + id));

        tripRepository.delete(trip);
    }
}
