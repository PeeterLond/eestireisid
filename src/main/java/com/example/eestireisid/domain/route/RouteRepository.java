package com.example.eestireisid.domain.route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Integer> {

    @Query("""
            select r from Route r
            where upper(r.fromCity.name) = upper(?1) and upper(r.toCity.name) = upper(?2) and upper(r.status) = upper(?3)""")
    Optional<Route> findActiveRouteBy(String fromName, String toName, String status);

}