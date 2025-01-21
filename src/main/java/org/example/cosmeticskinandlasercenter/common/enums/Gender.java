package org.example.cosmeticskinandlasercenter.common.enums;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Gender {

        MALE("Male"),
        FEMALE("Female"),
        OTHER("Other");

        private final String displayName;

        Gender(String displayName) {
                this.displayName = displayName;
        }

        public String getDisplayName() {
                return displayName;
        }

        private static final Map<String, Gender> ENUM_MAP = Collections.unmodifiableMap(
                Arrays.stream(values()).collect(Collectors.toMap(Gender::name, Function.identity()))
        );

        public static Gender fromString(String name) {
                return ENUM_MAP.get(name);
        }

}
