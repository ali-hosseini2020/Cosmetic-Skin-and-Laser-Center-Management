package org.example.cosmeticskinandlasercenter.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum AppointmentStatus {
    SCHEDULED("Scheduled"),
    CONFIRMED("Confirmed"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed"),
    RESCHEDULED("Rescheduled"),
    PENDING("Pending"),
    IN_PROGRESS("In Progress");

    private final String displayName;

    AppointmentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static final Map<String, AppointmentStatus> ENUM_MAP = Collections.unmodifiableMap(
            Arrays.stream(values()).collect(Collectors.toMap(AppointmentStatus::name, Function.identity()))
    );

    public static AppointmentStatus fromString(String name) {
        return ENUM_MAP.get(name);
    }
}