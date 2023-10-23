package com.example.eestireisid.domain.schedule;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Resource
    private ScheduleRepository scheduleRepository;


    public void saveSchedule(Schedule newSchedule) {
        scheduleRepository.save(newSchedule);
    }

    public Schedule getScheduleBy(Integer scheduleId) {
        return scheduleRepository.getReferenceById(scheduleId);
    }
}
