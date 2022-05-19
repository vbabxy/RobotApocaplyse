package com.robot.robotapocalypse.enums;

import java.util.Optional;

public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private String name;

    Gender(String name) {
        this.name = name;
    }

    public static Optional<Gender> getGender(String name) {

        for(Gender gender : values())
            if(gender.name.equalsIgnoreCase(name))
                return Optional.of(gender);

        return Optional.empty();
    }
}
