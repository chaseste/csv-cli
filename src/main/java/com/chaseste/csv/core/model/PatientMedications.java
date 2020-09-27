package com.chaseste.csv.core.model;

import static com.chaseste.csv.core.model.types.clinical.Medication.requiredMedications;
import static com.chaseste.csv.core.model.types.common.BasicPatient.requiredBasicPatient;
import static com.chaseste.csv.core.model.types.common.BasicVisit.requiredBasicVisit;

import java.util.List;

import com.chaseste.csv.core.model.types.clinical.Medication;
import com.chaseste.csv.core.model.types.common.BasicPatient;
import com.chaseste.csv.core.model.types.common.BasicVisit;
import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "patient", "encounter", "medications" })
public class PatientMedications {

    public static PatientMedications newPatientMedications(Fields fields) {
        PatientMedications meds = new PatientMedications();
        meds.patient = requiredBasicPatient(fields);
        meds.encounter = requiredBasicVisit(fields);
        meds.medications = requiredMedications(fields);
        return meds;
    }

    private BasicPatient patient;
    private BasicVisit encounter;
    private List<Medication> medications;

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

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}
