package com.example.eestireisid.domain.timetableroute;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TimetableRouteService {

    @Resource
    private TimetableRouteRepository timetableRouteRepository;


    public void save(TimetableRoute timetableRoute) {
        timetableRouteRepository.save(timetableRoute);
    }

    public TimetableRoute getTimetableRouteBy(Integer routeId) {
        return timetableRouteRepository.getTimetableRouteBy(routeId);
    }
}
