package com.example.demo;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EveryEnum implements SafeEnum {

    DAYS(0L, "d"),

    HOURS(1L, "h");


    private final long id;

    private final String name;

    EveryEnum(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static EveryEnum from(long id) {
        return Enums.from(EveryEnum.class, id);
    }

    @JsonCreator
    public static EveryEnum forValue(String name) {
        return Enums.from(EveryEnum.class, name);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

}