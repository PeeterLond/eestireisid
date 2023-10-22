package com.example.eestireisid.domain.timetable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.Optional;

public interface TimetableRepository extends JpaRepository<Timetable, Integer> {
    @Query("select t from Timetable t where t.expireDate = ?1")
    Optional<Timetable> findTimetableBy(Instant expireDate);


}