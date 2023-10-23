package com.example.eestireisid.domain.route;

import com.example.eestireisid.infrastructure.exception.DataNotFoundException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.eestireisid.infrastructure.Error.NO_ROUTE_FOUND;

@Service
public class RouteService {

    @Resource
    private RouteRepository routeRepository;

    public void saveRoute(Route newRoute) {
        routeRepository.save(newRoute);
    }

    public Route findRouteBy(String fromCity, String toCity) {
        Optional<Route> route = routeRepository.findActiveRouteBy(fromCity, toCity, "A");

        if (route.isEmpty()) {
            throw new DataNotFoundException(NO_ROUTE_FOUND.getMessage(), NO_ROUTE_FOUND.getErrorCode());
        }
        return route.get();
    }
}
