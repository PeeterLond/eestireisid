package com.example.eestireisid.domain.schedule;

import com.example.eestireisid.domain.schedule.Schedule;
import com.example.eestireisid.domain.schedule.ScheduleRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Resource
    private ScheduleRepository scheduleRepository;


    public void saveSchedule(Schedule newSchedule) {
        scheduleRepository.save(newSchedule);
    }
}
