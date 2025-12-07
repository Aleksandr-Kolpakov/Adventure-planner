package com.adv_planner.adventure_planner.controller;

import com.adv_planner.adventure_planner.dto.TripPointDTO;
import com.adv_planner.adventure_planner.entity.TripPoint;
import com.adv_planner.adventure_planner.service.TripPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TripPointController {

    private final TripPointService tripPointService;

    @Autowired
    public TripPointController(TripPointService tripPointService) {
        this.tripPointService = tripPointService;
    }


// create trip point
    @PostMapping("/api/trips/{tripId}/points")
    ResponseEntity<TripPoint> createTripPoint(@RequestBody TripPointDTO tripPointDTO) {
        TripPoint tripPoint = tripPointService.createTripPoint(tripPointDTO);

        return ResponseEntity.ok(tripPoint);
    }



// get all trip points
    @GetMapping("/api/trips/{tripId}/points")
    ResponseEntity<List<TripPoint>> getAllTripPoints(@PathVariable Long tripId) {
        List<TripPoint> list = tripPointService.getAllTripPoints(tripId);

        return ResponseEntity.ok(list);
    }


// get trip point by id
    @GetMapping("/api/trips/{tripId}/points/{pointId}")
    ResponseEntity<TripPoint> getTripPointById(@PathVariable Long tripId, @PathVariable Long pointId) {
        TripPoint tripPoint = tripPointService.getTripPointById(tripId, pointId);

        return ResponseEntity.ok(tripPoint);
    }


// update trip point
    @PatchMapping("/api/trips/{tripId}/points/{pointId}")
    ResponseEntity<TripPoint> updateTripPoint(@PathVariable Long tripId, @PathVariable Long pointId, @RequestBody TripPointDTO tripPointDTO) {
        return ResponseEntity.ok(tripPointService.updateTripPoint(tripId, pointId, tripPointDTO));
    }


// delete trip point
    @DeleteMapping("/api/trips/{tripId}/points/{pointId}")
    ResponseEntity<Void> deleteTripPoint(@PathVariable Long tripId, @PathVariable Long pointId) {
        tripPointService.deleteTripPoint(tripId, pointId);
        return ResponseEntity.noContent().build();
    }
}
