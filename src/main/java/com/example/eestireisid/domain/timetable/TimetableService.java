package com.example.eestireisid.domain.timetable;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class TimetableService {

    @Resource
    private TimetableRepository timetableRepository;


    public void saveTimetable(Timetable timetable) {
        timetableRepository.save(timetable);
    }

    public Optional<Timetable> findTimetableBy(Instant datetimeInstant) {
        return timetableRepository.findTimetableBy(datetimeInstant);
    }
}
