package com.batalova.autopark.dto;

import java.util.Optional;

public class RouteDto {

    private final Optional<Integer> id;

    private final String name;

    public RouteDto(Optional<Integer> id, String name) {
        this.id = id;
        this.name = name;
    }

    public Optional<Integer> getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
