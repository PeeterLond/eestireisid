package com.example.eestireisid.domain.schedule;

import com.example.eestireisid.business.dto.ScheduleDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ScheduleMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "startTime.dateTime", target = "startTimeDateTime")
    @Mapping(source = "endTime.dateTime", target = "endTimeDateTime")
    @Mapping(source = "company.name", target = "companyName")
    @Mapping(source = "price", target = "price")
    ScheduleDto toScheduleDto(Schedule schedule);

    List<ScheduleDto> toScheduleDtos(List<Schedule> schedules);

}