package com.example.eestireisid.domain.routeschedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteScheduleRepository extends JpaRepository<RouteSchedule, Integer> {
    @Query("select r from RouteSchedule r where r.route.id = ?1")
    List<RouteSchedule> getRouteSchedulesBy(Integer routeId);

    @Query("select r from RouteSchedule r where r.schedule.id = ?1")
    RouteSchedule getRouteScheduleBy(Integer routeId);




}