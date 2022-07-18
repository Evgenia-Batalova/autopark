package com.batalova.autopark.dto;

import java.time.Instant;
import java.util.Optional;

public class JournalDto {

    private final Optional<Integer> id;

    private final int autoId;

    private final int routeId;

    private final Instant timeIn;

    private final Optional<Instant> timeOut;

    public JournalDto(Optional<Integer> id, Integer autoId, Integer routeId, Instant timeIn, Optional<Instant> timeOut) {
        this.id = id;
        this.autoId = autoId;
        this.routeId = routeId;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public JournalDto(Integer autoId, Integer routeId, Instant timeIn) {
        this(Optional.empty(), autoId, routeId, timeIn, Optional.empty());
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

    public Optional<Instant> getTimeOut() {
        return timeOut;
    }
}
