package com.plottheirfuture.plottheirfuture_backend.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum WebAppType {
    app,
    website;

    @JsonValue
    public String getValue() {
        return this.name();
    }

    @JsonCreator
    public static WebAppType fromString(String value) {
        if (value == null) {
            return null;
        }
        for (WebAppType type : WebAppType.values()) {
            if (type.name().equalsIgnoreCase(value.trim())) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid type: '" + value + "'. Type must only be either 'app' or 'website'.");
    }
}
