package com.example.eestireisid.business.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto implements Serializable {
    private Integer id;
    private Instant startTimeDateTime;
    private Instant endTimeDateTime;
    private String companyName;
    @NotNull
    private Float price;
}