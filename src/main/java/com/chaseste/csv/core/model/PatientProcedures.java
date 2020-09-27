package com.chaseste.csv.core.model;

import static com.chaseste.csv.core.model.types.clinical.Procedure.requiredProcedures;
import static com.chaseste.csv.core.model.types.common.BasicPatient.requiredBasicPatient;
import static com.chaseste.csv.core.model.types.common.BasicVisit.requiredBasicVisit;

import java.util.List;

import com.chaseste.csv.core.model.types.clinical.Procedure;
import com.chaseste.csv.core.model.types.common.BasicPatient;
import com.chaseste.csv.core.model.types.common.BasicVisit;
import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "patient", "encounter", "procedures" })
public class PatientProcedures {

    public static PatientProcedures newPatientProcedures(Fields fields) {
        PatientProcedures procedures = new PatientProcedures();
        procedures.patient = requiredBasicPatient(fields);
        procedures.encounter = requiredBasicVisit(fields);
        procedures.procedures = requiredProcedures(fields);
        return procedures;
    }

    private BasicPatient patient;
    private BasicVisit encounter;
    private List<Procedure> procedures;

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

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }
}
