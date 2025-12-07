package com.adv_planner.adventure_planner.controller;

import com.adv_planner.adventure_planner.dto.TripDTO;
import com.adv_planner.adventure_planner.entity.Trip;
import com.adv_planner.adventure_planner.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }


// create trip
    @PostMapping("/api/trips")
    ResponseEntity<Trip> createTrip(@RequestBody TripDTO tripDto) {
        Trip trip = tripService.createTrip(tripDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(trip);
    }


// get trip by id
    @GetMapping("/api/trips/{id}")
    ResponseEntity<Trip> getTrip(@PathVariable Long id) {
        Trip trip = tripService.getTripById(id);

        return ResponseEntity.ok(trip);
    }


// get all trips
    @GetMapping("/api/trips")
    ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> tripList = tripService.getAllTrips();

        return ResponseEntity.ok(tripList);
    }


// update trip
    @PutMapping("/api/trips/{id}")
    ResponseEntity<Trip> updateTrip(@PathVariable Long id, @RequestBody TripDTO tripDto) {
        Trip trip = tripService.updateTrip(id, tripDto);

        return ResponseEntity.ok(trip);
    }


// delete trip
    @DeleteMapping("/api/trips/{id}")
    ResponseEntity<Trip> deleteTrip(@PathVariable Long id) {
        try {
            tripService.deleteTrip(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
