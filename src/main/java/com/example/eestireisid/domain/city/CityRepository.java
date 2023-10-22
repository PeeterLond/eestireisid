package com.example.eestireisid.domain.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    @Query("select c from City c where c.name = ?1")
    Optional<City> findCityBy(String name);

}