package org.example.cosmeticskinandlasercenter.common.enums;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum StaffRole {

    DOCTOR("Doctor"),
    NURSE("Nurse"),
    TECHNICIAN("Technician"),
    RECEPTIONIST("Receptionist"),
    MANAGER("Manager"),
    ADMINISTRATOR("Administrator"),
    SPECIALIST("Specialist"),
    THERAPIST("Therapist");

    private final String displayName;

    StaffRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static final Map<String, StaffRole> ENUM_MAP = Collections.unmodifiableMap(
            Arrays.stream(values()).collect(Collectors.toMap(StaffRole::name, Function.identity()))
    );

    public static StaffRole fromString(String name) {
        return ENUM_MAP.get(name);
    }
}
