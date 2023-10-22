package com.example.eestireisid.domain.city;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {

    @Resource
    private CityRepository cityRepository;

    public Optional<City> findCityBy(String fromCityString) {
        return cityRepository.findCityBy(fromCityString);
    }

    public void saveCity(City city) {
        cityRepository.save(city);
    }
}
