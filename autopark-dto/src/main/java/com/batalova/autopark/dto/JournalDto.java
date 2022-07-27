package com.batalova.autopark.dto;

import java.time.Instant;
import java.util.Optional;

public class JournalDto {

    private final Optional<Integer> id;

    private final int auto_id;

    private final int route_id;

    private final Instant time_in;

    private final Optional<Instant> time_out;

    public JournalDto(Optional<Integer> id, Integer autoId, Integer route_id, Instant time_in, Optional<Instant> time_out) {
        this.id = id;
        this.auto_id = autoId;
        this.route_id = route_id;
        this.time_in = time_in;
        this.time_out = time_out;
    }

    public Optional<Integer> getId() {
        return id;
    }

    public int getAutoId() {
        return auto_id;
    }

    public int getRouteId() {
        return route_id;
    }

    public Instant getTimeIn() {
        return time_in;
    }

    public Optional<Instant> getTimeOut() {
        return time_out;
    }
}
