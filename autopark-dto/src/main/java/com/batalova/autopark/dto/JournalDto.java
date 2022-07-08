package com.batalova.autopark.dto;

import java.time.Instant;
import java.util.Optional;

public class JournalDto {

    private final Optional<Integer> id;

    private final int autoId;

    private final int routeId;

    private final Instant timeIn;

    private final Instant timeOut;

    private JournalDto(Optional<Integer> id, Integer autoId, Integer routeId, Instant timeIn, Instant timeOut) {
        this.id = id;
        this.autoId = autoId;
        this.routeId = routeId;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public Optional<Integer> getId() {
        return id;
    }

    public int getAutoId() {
        return autoId;
    }

    public int getRouteId() {
        return routeId;
    }

    public Instant getTimeIn() {
        return timeIn;
    }

    public Instant getTimeOut() {
        return timeOut;
    }
}
