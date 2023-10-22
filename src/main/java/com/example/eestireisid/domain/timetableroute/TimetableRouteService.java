package com.example.eestireisid.domain.timetableroute;

import com.example.eestireisid.domain.timetableroute.TimetableRoute;
import com.example.eestireisid.domain.timetableroute.TimetableRouteRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TimetableRouteService {

    @Resource
    private TimetableRouteRepository timetableRouteRepository;


    public void save(TimetableRoute timetableRoute) {
        timetableRouteRepository.save(timetableRoute);
    }
}
