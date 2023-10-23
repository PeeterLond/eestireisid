package com.example.eestireisid.infrastructure;


import lombok.Getter;

@Getter
public enum Error {

    NO_ROUTE_FOUND("Sellist teekonda pole hetkel saadaval", 111),
    TIMETABLE_UPDATE("Ajagraafik on uuenenud, tee uus otsing", 222);

    private final String message;
    private final int errorCode;

    Error(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
