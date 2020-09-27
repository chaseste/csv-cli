package com.chaseste.csv.core.mapper;

import java.util.HashMap;
import java.util.Map;

import com.chaseste.csv.core.model.Patient;
import com.chaseste.csv.core.model.PatientAllergys;
import com.chaseste.csv.core.model.PatientImmunizations;
import com.chaseste.csv.core.model.PatientMedications;
import com.chaseste.csv.core.model.PatientProblems;
import com.chaseste.csv.core.model.PatientProcedures;
import com.chaseste.csv.core.model.PatientVisits;

/**
 * {@link ModelMapper) Factory
 */
public class Mappers {
    private static final Map<Models, ModelMapper<?>> MAPPERS;
    static
    {
        MAPPERS = new HashMap<>();
        MAPPERS.put(Models.PATIENTS, (fields) -> Patient.newPatient(fields));
        MAPPERS.put(Models.VISITS, (fields) -> PatientVisits.newPatientVisits(fields));
        MAPPERS.put(Models.ALLERGYS, (fields) -> PatientAllergys.newPatientAllergys(fields));
        MAPPERS.put(Models.PROBLEMS, (fields) -> PatientProblems.newPatientProblems(fields));
        MAPPERS.put(Models.MEDICATIONS, (fields) -> PatientMedications.newPatientMedications(fields));
        MAPPERS.put(Models.IMMUNIZATIONS, (fields) -> PatientImmunizations.newPatientImmunizations(fields));
        MAPPERS.put(Models.PROCEDURES, (fields) -> PatientProcedures.newPatientProcedures(fields));
    }

    /**
     * Constructs a {@link ModelMapper} for the specifed model
     * 
     * @param model the model. May not be {@code null}
     * @return the {@link ModelMapper}. Will not be {@code null}
     */
    public ModelMapper<?> mapper(Models model) {
        return MAPPERS.get(model);
    }
}
