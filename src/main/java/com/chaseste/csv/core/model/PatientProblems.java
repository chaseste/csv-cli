package com.chaseste.csv.core.model;

import static com.chaseste.csv.core.model.types.clinical.Problem.requiredProblems;
import static com.chaseste.csv.core.model.types.common.BasicPatient.requiredBasicPatient;

import java.util.List;

import com.chaseste.csv.core.model.types.clinical.Problem;
import com.chaseste.csv.core.model.types.common.BasicPatient;
import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "patient", "problems" })
public class PatientProblems {

    public static PatientProblems newPatientProblems(Fields fields) {
        PatientProblems problems = new PatientProblems();
        problems.patient = requiredBasicPatient(fields);
        problems.problems = requiredProblems(fields);
        return problems;
    }
    
    private BasicPatient patient;
    private List<Problem> problems;

    public BasicPatient getPatient() {
        return patient;
    }

    public void setPatient(BasicPatient patient) {
        this.patient = patient;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }
}
