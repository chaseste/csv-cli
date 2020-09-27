package com.chaseste.csv.core.model;

import static com.chaseste.csv.core.model.types.clinical.Allergy.requiredAllergys;
import static com.chaseste.csv.core.model.types.common.BasicPatient.requiredBasicPatient;
import static com.chaseste.csv.core.model.types.common.BasicVisit.requiredBasicVisit;

import java.util.List;

import com.chaseste.csv.core.model.types.clinical.Allergy;
import com.chaseste.csv.core.model.types.common.BasicPatient;
import com.chaseste.csv.core.model.types.common.BasicVisit;
import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "patient", "encounter", "allergys" })
public class PatientAllergys {

    public static PatientAllergys newPatientAllergys(Fields fields) {
        PatientAllergys allergies = new PatientAllergys();
        allergies.patient = requiredBasicPatient(fields);
        allergies.encounter = requiredBasicVisit(fields);
        allergies.allergys = requiredAllergys(fields);
        return allergies;
    }

    private BasicPatient patient;
    private BasicVisit encounter;
    private List<Allergy> allergys;

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

    public List<Allergy> getAllergys() {
        return allergys;
    }

    public void setAllergys(List<Allergy> allergys) {
        this.allergys = allergys;
    }
}
