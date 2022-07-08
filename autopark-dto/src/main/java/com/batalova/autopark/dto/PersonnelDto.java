package com.batalova.autopark.dto;

import java.util.Optional;

public class PersonnelDto {

    private final Optional<Integer> id;

    private final String firstName;

    private final String lastName;

    private final String fatherName;

    public PersonnelDto (Optional<Integer> id, String firstName, String lastName, String fatherName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
    }

    public Optional<Integer> getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }
}

