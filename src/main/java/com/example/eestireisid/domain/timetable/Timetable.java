package com.example.eestireisid.domain.timetable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "timetable")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "expire_date", nullable = false)
    private Instant expireDate;

    @NotNull
    @Column(name = "timezone_type", nullable = false)
    private Integer timezoneType;

    @Size(max = 255)
    @NotNull
    @Column(name = "timezone", nullable = false)
    private String timezone;

}