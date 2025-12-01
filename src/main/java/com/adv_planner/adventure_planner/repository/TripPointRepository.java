package com.adv_planner.adventure_planner.repository;

import com.adv_planner.adventure_planner.entity.TripPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripPointRepository extends JpaRepository<TripPoint, Long> {
}
