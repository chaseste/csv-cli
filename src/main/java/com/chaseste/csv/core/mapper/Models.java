package com.chaseste.csv.core.mapper;

import java.util.List;
import java.util.Arrays;

/**
 * Supported Models
 */
public enum Models {
    PATIENTS("Patient Demographics", Arrays.asList("p", "d", "patients")),
    VISITS("Patient Visits", Arrays.asList("v", "visits")),
    ALLERGYS("Patient Allergies", Arrays.asList("a", "allergys")),
    PROBLEMS("Patient Problems", Arrays.asList("prob", "problems")),
    MEDICATIONS("Patient Medications", Arrays.asList("m", "medications")),
    IMMUNIZATIONS("Patient Immunizations", Arrays.asList("i", "immunizations")),
    PROCEDURES("Patient Procedures", Arrays.asList("proc", "procedures"));

    private final String display;
    private final List<String> aliases;

    private Models(String descrip, List<String> aliases) {
        this.aliases = aliases;
        display = String.format("%s: %s", descrip, aliases);
    }

    public String getDisplay() {
        return display;
    }

    public String getDirectoryName() {
        return name().toLowerCase();
    }

    public static Models toModel(String alias) {
        for (Models m : values()) {
            if (m.aliases.contains(alias)) {
                return m;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid Model [%s].", alias));
    }
}