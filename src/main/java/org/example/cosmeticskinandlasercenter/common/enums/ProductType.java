package org.example.cosmeticskinandlasercenter.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ProductType {
    SKINCARE("Skincare"),
    LASER_EQUIPMENT("Laser Equipment"),
    MEDICAL_SUPPLIES("Medical Supplies"),
    COSMETICS("Cosmetics"),
    INJECTABLES("Injectables"),
    DISPOSABLES("Disposables"),
    OTHER("Other");

    private final String displayName;

    ProductType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static final Map<String, ProductType> ENUM_MAP = Collections.unmodifiableMap(
            Arrays.stream(values()).collect(Collectors.toMap(ProductType::name, Function.identity()))
    );

    public static ProductType fromString(String name) {
        return ENUM_MAP.get(name);
    }
}
