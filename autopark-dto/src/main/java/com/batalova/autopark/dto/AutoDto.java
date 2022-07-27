package com.batalova.autopark.dto;

import java.util.Optional;

public class AutoDto {
    private final Optional<Integer> id;
    private final int personnel_id;
    private final String color;
    private final String num;
    private final String mark;

    public AutoDto(Optional<Integer> id, Integer personnel_id, String color, String num, String mark) {
        this.id = id;
        this.personnel_id = personnel_id;
        this.color = color;
        this.num = num;
        this.mark = mark;
    }

    public Optional<Integer> getId() {
        return id;
    }

    public int getPersonnelId() {
        return personnel_id;
    }

    public String getColor() {
        return color;
    }

    public String getMark() {
        return mark;
    }

    public String getNumber() {
        return num;
    }
}
