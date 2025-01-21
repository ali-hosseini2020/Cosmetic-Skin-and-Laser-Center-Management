package org.example.cosmeticskinandlasercenter.common.enums;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum TreatmentType {
    LASER_HAIR_REMOVAL("Laser Hair Removal"),
    FACIAL("Facial"),
    BOTOX("Botox"),
    FILLER("Filler"),
    CHEMICAL_PEEL("Chemical Peel"),
    MICRODERMABRASION("Microdermabrasion"),
    DERMAL_FILLER("Dermal Filler"),
    THREAD_LIFT("Thread Lift"),
    COOLSCULPTING("CoolSculpting"),
    FACE_TREATMENT("Face Treatment"),
    BODY_TREATMENT("Body Treatment"),
    HAIR_TREATMENT("Hair Treatment"),
    LASER_TREATMENT("Laser Treatment"),
    INJECTABLE_TREATMENT("Injectable Treatment"),
    NON_INVASIVE_TREATMENT("Non-Invasive Treatment"),
    MESOTHERAPY("Mesotherapy"),
    PRP("PRP"),
    HAIR_TRANSPLANT("Hair Transplant"),
    SKIN_REJUVENATION("Skin Rejuvenation"),
    CELLULITE_REDUCTION("Cellulite Reduction");

    private final String displayName;

    TreatmentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static final Map<String, TreatmentType> ENUM_MAP = Collections.unmodifiableMap(
            Arrays.stream(values()).collect(Collectors.toMap(TreatmentType::name, Function.identity()))
    );

    public static TreatmentType fromString(String name) {
        return ENUM_MAP.get(name);
    }
}