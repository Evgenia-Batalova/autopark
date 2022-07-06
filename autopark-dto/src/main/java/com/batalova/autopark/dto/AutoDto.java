package com.batalova.autopark.dto;

import java.util.Optional;

public class AutoDto {
    private final Optional<Integer> id;
    private final int personnelId;
    private final String color;
    private final String number;
    private final String mark;

    public AutoDto(Optional<Integer> id, Integer personnelId, String color, String number, String mark) {
        this.id = id;
        this.personnelId = personnelId;
        this.color = color;
        this.number = number;
        this.mark = mark;
    }

    public Optional<Integer> getId() {
        return id;
    }

    public int getPersonnelId() {
        return personnelId;
    }

    public String getColor() {
        return color;
    }

    public String getMark() {
        return mark;
    }

    public String getNumber() {
        return number;
    }
}
