package com.adv_planner.adventure_planner.dto;


import java.time.LocalDateTime;

public class TripPointDTO {
    private String title;
    private String description;
    private String address;
    private LocalDateTime plannedDate;
    private Long trip_id;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getPlannedDate() {
        return plannedDate;
    }

    public Long getTrip_id() {
        return trip_id;
    }
}