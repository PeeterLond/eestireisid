package com.example.eestireisid.domain.timetableroute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TimetableRouteRepository extends JpaRepository<TimetableRoute, Integer> {
    @Query("select t from TimetableRoute t where t.route.id = ?1")
    TimetableRoute getTimetableRouteBy(Integer routeId);


}