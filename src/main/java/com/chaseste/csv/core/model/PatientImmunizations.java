package com.chaseste.csv.core.model;

import static com.chaseste.csv.core.model.types.clinical.Immunization.requiredImmunizations;
import static com.chaseste.csv.core.model.types.common.BasicPatient.requiredBasicPatient;
import static com.chaseste.csv.core.model.types.common.BasicVisit.requiredBasicVisit;

import java.util.List;

import com.chaseste.csv.core.model.types.clinical.Immunization;
import com.chaseste.csv.core.model.types.common.BasicPatient;
import com.chaseste.csv.core.model.types.common.BasicVisit;
import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "patient", "encounter", "immunizations" })
public class PatientImmunizations {

    public static PatientImmunizations newPatientImmunizations(Fields fields) {
        PatientImmunizations immunizations = new PatientImmunizations();
        immunizations.patient = requiredBasicPatient(fields);
        immunizations.encounter = requiredBasicVisit(fields);
        immunizations.immunizations = requiredImmunizations(fields);
        return immunizations;
    }

    private BasicPatient patient;
    private BasicVisit encounter;
    private List<Immunization> immunizations;

    public BasicPatient getPatient() {
        return patient;
    }

    public void setPatient(BasicPatient patient) {
        this.patient = patient;
    }

    public BasicVisit getEncounter() {
        return encounter;
    }

    public void setEncounter(BasicVisit encounter) {
        this.encounter = encounter;
    }

    public List<Immunization> getImmunizations() {
        return immunizations;
    }

    public void setImmunizations(List<Immunization> immunizations) {
        this.immunizations = immunizations;
    }
}
