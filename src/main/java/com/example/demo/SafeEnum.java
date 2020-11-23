package com.example.demo;

import com.fasterxml.jackson.annotation.JsonValue;

public interface SafeEnum {

    long getId();

    @JsonValue
    String getName();

}