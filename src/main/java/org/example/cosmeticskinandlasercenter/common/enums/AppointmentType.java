package org.example.cosmeticskinandlasercenter.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum AppointmentType {

    SURGERY("Surgery"),
    CONSULTATION("Consultation"),
    TREATMENT("Treatment"),
    FOLLOW_UP("Follow Up"),
    CHECKUP("Checkup"),
    OTHER("Other");

    private String displayName;

    AppointmentType(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }

    private static final Map<String, AppointmentType> ENUM_MAP = Collections.unmodifiableMap(
            Arrays.stream(values()).collect(Collectors.toMap(AppointmentType::name, Function.identity()))
    );
    public static AppointmentType fromString(String name) {
        return ENUM_MAP.get(name);
    }
}

