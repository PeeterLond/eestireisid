package com.example.eestireisid.business.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto implements Serializable {
    private String fromCityName;
    private String toCityName;
    @NotNull
    private Integer distance;
    private List<ScheduleDto> schedules;
}