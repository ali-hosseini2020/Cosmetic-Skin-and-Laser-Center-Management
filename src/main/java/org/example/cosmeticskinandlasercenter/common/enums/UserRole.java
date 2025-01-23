package org.example.cosmeticskinandlasercenter.common.enums;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum UserRole {

        ADMIN("Admin"),
        STAFF("Staff"),
        PATIENT("Patient");

        private final String displayName;

        UserRole(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

        private static final Map<String, UserRole> ENUM_MAP = Collections.unmodifiableMap(
                Arrays.stream(values()).collect(Collectors.toMap(UserRole::name, Function.identity()))
        );

        public static UserRole fromString(String name) {
            return ENUM_MAP.get(name);
        }
}
