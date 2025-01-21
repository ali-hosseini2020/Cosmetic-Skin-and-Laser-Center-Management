package org.example.cosmeticskinandlasercenter.common.enums;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ConditionType {

    ACNE("Acne"),
    ROSACEA("Rosacea"),
    ECZEMA("Eczema"),
    PSORIASIS("Psoriasis"),
    WRINKLES("Wrinkles"),
    AGE_SPOTS("Age Spots"),
    SCARS("Scars"),
    DRY_SKIN("Dry Skin"),
    OILY_SKIN("Oily Skin"),
    SENSITIVE_SKIN("Sensitive Skin"),
    HYPERPIGMENTATION("Hyperpigmentation"),
    MELASMA("Melasma"),
    SUN_DAMAGE("Sun Damage"),
    LARGE_PORES("Large Pores"),
    BLACKHEADS("Blackheads"),
    WHITEHEADS("Whiteheads"),
    FINE_LINES("Fine Lines"),
    SAGGING_SKIN("Sagging Skin"),
    DEHYDRATED_SKIN("Dehydrated Skin"),
    COMBINATION_SKIN("Combination Skin"),
    SEBORRHEIC_DERMATITIS("Seborrheic Dermatitis"),
    CONTACT_DERMATITIS("Contact Dermatitis");

    private final String displayName;

    ConditionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static final Map<String, ConditionType> ENUM_MAP = Collections.unmodifiableMap(
            Arrays.stream(values()).collect(Collectors.toMap(ConditionType::name, Function.identity()))
    );

    public static ConditionType fromString(String name) {
        return ENUM_MAP.get(name);
    }
}
