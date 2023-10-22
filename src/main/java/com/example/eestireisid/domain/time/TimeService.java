package com.example.eestireisid.domain.time;

import com.example.eestireisid.domain.time.Time;
import com.example.eestireisid.domain.time.TimeRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    @Resource
    private TimeRepository timeRepository;

    public void save(Time newTime) {
        timeRepository.save(newTime);
    }
}
