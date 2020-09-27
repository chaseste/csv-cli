package com.chaseste.csv.core.model;

import static com.chaseste.csv.core.model.types.clinical.Visit.requiredVisits;
import static com.chaseste.csv.core.model.types.common.BasicPatient.requiredBasicPatient;

import java.util.List;

import com.chaseste.csv.core.model.types.clinical.Visit;
import com.chaseste.csv.core.model.types.common.BasicPatient;
import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "patient", "encounter" })
public class PatientVisits {

    public static PatientVisits newPatientVisits(Fields fields) {
        PatientVisits visit = new PatientVisits();
        visit.patient = requiredBasicPatient(fields);
        visit.encounters = requiredVisits(fields);
        return visit;
    }
    
    private BasicPatient patient;
    private List<Visit> encounters;

    public BasicPatient getPatient() {
        return patient;
    }

    public void setPatient(BasicPatient patient) {
        this.patient = patient;
    }

    public List<Visit> getEncounters() {
        return encounters;
    }

    public void setEncounters(List<Visit> encounters) {
        this.encounters = encounters;
    }
}
