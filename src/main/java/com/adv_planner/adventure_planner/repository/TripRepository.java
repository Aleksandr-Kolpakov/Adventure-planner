package com.adv_planner.adventure_planner.repository;

import com.adv_planner.adventure_planner.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
