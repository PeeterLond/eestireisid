package com.example.eestireisid.domain.route;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    @Resource
    private RouteRepository routeRepository;

    public void saveRoute(Route newRoute) {
        routeRepository.save(newRoute);
    }
}
