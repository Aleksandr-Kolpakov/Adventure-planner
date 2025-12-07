package com.adv_planner.adventure_planner.service;

import com.adv_planner.adventure_planner.dto.TripPointDTO;
import com.adv_planner.adventure_planner.entity.Trip;
import com.adv_planner.adventure_planner.entity.TripPoint;
import com.adv_planner.adventure_planner.repository.TripPointRepository;
import com.adv_planner.adventure_planner.repository.TripRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Service
@Transactional
public class TripPointService {

    private final TripService tripService;
    private final TripPointRepository tripPointRepository;

    private final TripRepository tripRepository;

    @Autowired
    public TripPointService(TripService tripService, TripPointRepository tripPointRepository,
                            TripRepository tripRepository) {
        this.tripService = tripService;
        this.tripPointRepository = tripPointRepository;
        this.tripRepository = tripRepository;
    }


    private TripPoint getTripPointBelongingToTrip(Long tripId, Long tripPointId) {
        Trip trip = tripService.getTripById(tripId);
        TripPoint point = tripPointRepository.findById(tripPointId).orElseThrow(() -> new RuntimeException("Trip point not found"));

        if (!point.getTrip().getId().equals(trip.getId())) {
            throw new RuntimeException("Trip point not belonging go trip");
        }

        return point;
    }


// create trip point
    public TripPoint createTripPoint(TripPointDTO tripPointDTO) {
        Trip trip = tripService.getTripById(tripPointDTO.getTrip_id());
        TripPoint tripPoint = new TripPoint();

        tripPoint.setTitle(tripPointDTO.getTitle());
        tripPoint.setDescription(tripPointDTO.getDescription());
        tripPoint.setAddress(tripPointDTO.getAddress());
        tripPoint.setPlannedDate(tripPointDTO.getPlannedDate());
        tripPoint.setTrip(trip);

        return tripPointRepository.save(tripPoint);
    }


// get trip point by id
    public TripPoint getTripPointById(Long tripId, Long pointId) {
        return getTripPointBelongingToTrip(tripId, pointId);
    }


// get all trip points
    public List<TripPoint> getAllTripPoints(Long tripId) {
        return tripPointRepository.findByTripId(tripId);
    }


// update trip point
    public TripPoint updateTripPoint(Long tripId, Long pointId, TripPointDTO tripPointDTO) {
        TripPoint tripPoint = getTripPointBelongingToTrip(tripId, pointId);

        if (tripPointDTO.getTitle() != null) tripPoint.setTitle(tripPointDTO.getTitle());
        if (tripPointDTO.getDescription() != null) tripPoint.setDescription(tripPointDTO.getDescription());
        if (tripPointDTO.getAddress() != null) tripPoint.setAddress(tripPointDTO.getAddress());
        if (tripPointDTO.getPlannedDate() != null) tripPoint.setPlannedDate(tripPointDTO.getPlannedDate());

        return tripPointRepository.save(tripPoint);
    }


// delete trip point
    public void deleteTripPoint(Long tripId, Long tripPointId) {
       TripPoint point = getTripPointBelongingToTrip(tripId, tripPointId);
       tripPointRepository.deleteById(point.getId());
    }
}
