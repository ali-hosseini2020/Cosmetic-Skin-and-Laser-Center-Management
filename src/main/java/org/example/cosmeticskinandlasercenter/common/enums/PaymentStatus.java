package org.example.cosmeticskinandlasercenter.common.enums;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum PaymentStatus {
    PENDING("Pending"),
    PAID("Paid"),
    FAILED("Failed"),
    REFUNDED("Refunded"),
    PARTIALLY_PAID("Partially Paid");

    private final String displayName;

    PaymentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    private static final Map<String, PaymentStatus> ENUM_MAP = Collections.unmodifiableMap(
            Arrays.stream(values()).collect(Collectors.toMap(PaymentStatus::name, Function.identity()))
    );

    public static PaymentStatus fromString(String name) {
        return ENUM_MAP.get(name);
    }
}
