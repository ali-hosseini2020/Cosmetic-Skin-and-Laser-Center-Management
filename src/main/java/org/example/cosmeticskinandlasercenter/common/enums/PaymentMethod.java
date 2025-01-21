package org.example.cosmeticskinandlasercenter.common.enums;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum PaymentMethod  {

    CASH("Cash"),
    CREDIT_CARD("Credit Card"),
    DEBIT_CARD("Debit Card"),
    INSURANCE("Insurance"),
    BANK_TRANSFER("Bank Transfer"),
    ONLINE_PAYMENT("Online Payment");

    private final String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static final Map<String, PaymentMethod> ENUM_MAP = Collections.unmodifiableMap(
            Arrays.stream(values()).collect(Collectors.toMap(PaymentMethod::name, Function.identity()))
    );

    public static PaymentMethod fromString(String name) {
        return ENUM_MAP.get(name);
    }
}
