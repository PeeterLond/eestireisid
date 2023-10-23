package com.example.eestireisid.domain.routeschedule;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteScheduleService {

    @Resource
    private RouteScheduleRepository routeScheduleRepository;


    public void saveRouteSchedule(RouteSchedule routeSchedule) {
        routeScheduleRepository.save(routeSchedule);
    }


    public List<RouteSchedule> getAllRouteSchedulesBy(Integer routeId) {
        return routeScheduleRepository.getRouteSchedulesBy(routeId);
    }

    public RouteSchedule getRouteScheduleBy(Integer scheduleId) {
        return routeScheduleRepository.getRouteScheduleBy(scheduleId);
    }
}
