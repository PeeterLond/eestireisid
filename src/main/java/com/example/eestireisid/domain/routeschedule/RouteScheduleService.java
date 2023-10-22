package com.example.eestireisid.domain.routeschedule;

import com.example.eestireisid.domain.routeschedule.RouteScheduleRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class RouteScheduleService {

    @Resource
    private RouteScheduleRepository routeScheduleRepository;


    public void saveRouteSchedule(RouteSchedule routeSchedule) {
        routeScheduleRepository.save(routeSchedule);
    }
}
