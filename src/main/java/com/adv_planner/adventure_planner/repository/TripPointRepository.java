package com.adv_planner.adventure_planner.repository;

import com.adv_planner.adventure_planner.entity.TripPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripPointRepository extends JpaRepository<TripPoint, Long> {
    List<TripPoint> findByTripId(Long tripId);
}
