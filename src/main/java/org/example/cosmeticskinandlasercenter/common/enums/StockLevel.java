package org.example.cosmeticskinandlasercenter.common.enums;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum StockLevel {

    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High"),
    OUT_OF_STOCK("Out of Stock"),
    REORDER_POINT("Reorder Point");

    private final String displayName;

    StockLevel(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static final Map<String, StockLevel> ENUM_MAP = Collections.unmodifiableMap(
            Arrays.stream(values()).collect(Collectors.toMap(StockLevel::name, Function.identity()))
    );

    public static StockLevel fromString(String name) {
        return ENUM_MAP.get(name);
    }
}
