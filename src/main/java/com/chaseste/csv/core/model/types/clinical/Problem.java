package com.chaseste.csv.core.model.types.clinical;

import java.util.Arrays;
import java.util.List;

import com.chaseste.csv.core.model.types.common.Code;
import com.chaseste.csv.core.model.types.common.Comment;
import com.chaseste.csv.core.model.types.common.Physician;
import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "action_dt_tm",
    "condition",
    "management_discipline",
    "persistence",
    "confirmation_status",
    "life_cycle_status",
    "status_dt_tm",
    "onset_dt_tm",
    "ranking",
    "certainty",
    "individual_awareness",
    "prognosis",
    "individual_awareness_prognosis",
    "family_awareness",
    "classification",
    "cancel_reason",
    "severity",
    "severity_class",
    "comments",
    "physician",
    "annotated_display"
})
public class Problem {
    
    public static List<Problem> requiredProblems(Fields fields) {
        Problem problem = new Problem();
        problem.actionDtTm = fields.requiredString(5);
        problem.condition = Code.requiredCode(fields.optionalField(6));
        problem.managementDiscipline = Code.optionalCodes(fields.optionalRepeatField(7));
        problem.persistence = Code.optionalCode(fields.optionalField(8));
        problem.confirmationStatus = Code.optionalCode(fields.optionalField(9));
        problem.lifeCycleStatus = Code.optionalCode(fields.optionalField(10));
        problem.statusDtTm = fields.optionalString(11);
        problem.onsetDtTm = fields.optionalString(12);
        problem.ranking = Code.optionalCode(fields.optionalField(13));
        problem.certainty = Code.optionalCode(fields.optionalField(14));
        problem.individualAwareness = Code.optionalCode(fields.optionalField(15));
        problem.prognosis = Code.optionalCode(fields.optionalField(16));
        problem.individualAwarenessPrognosis = Code.optionalCode(fields.optionalField(17));
        problem.familyAwareness = Code.optionalCode(fields.optionalField(18));
        problem.classification = Code.optionalCode(fields.optionalField(19));
        problem.cancelReason = Code.optionalCode(fields.optionalField(20));
        problem.severity = Code.optionalCode(fields.optionalField(21));
        problem.severityClass = Code.optionalCode(fields.optionalField(22));
        problem.comments = Comment.optionalComments(fields.optionalRepeatField(23));
        problem.physician = Physician.optionalPhysician(fields.optionalField(24));
        problem.annotatedDisplay = fields.optionalString(25);
        return Arrays.asList(problem);
    }

    private String actionDtTm;
    private Code condition;
    private List<Code> managementDiscipline;
    private Code persistence;
    private Code confirmationStatus;
    private Code lifeCycleStatus;
    private String statusDtTm;
    private String onsetDtTm;
    private Code ranking;
    private Code certainty;
    private Code individualAwareness;
    private Code prognosis;
    private Code individualAwarenessPrognosis;
    private Code familyAwareness;
    private Code classification;
    private Code cancelReason;
    private Code severity;
    private Code severityClass;
    private List<Comment> comments;
    private Physician physician;
    private String annotatedDisplay;

    @JsonProperty("action_dt_tm")
    public String getActionDtTm() {
        return actionDtTm;
    }

    public void setActionDtTm(String actionDtTm) {
        this.actionDtTm = actionDtTm;
    }

    public Code getCondition() {
        return condition;
    }

    public void setCondition(Code condition) {
        this.condition = condition;
    }

    @JsonProperty("management_discipline")
    public List<Code> getManagementDiscipline() {
        return managementDiscipline;
    }

    public void setManagementDiscipline(List<Code> managementDiscipline) {
        this.managementDiscipline = managementDiscipline;
    }

    public Code getPersistence() {
        return persistence;
    }

    public void setPersistence(Code persistence) {
        this.persistence = persistence;
    }

    @JsonProperty("confirmation_status")
    public Code getConfirmationStatus() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(Code confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

    @JsonProperty("life_cycle_status")
    public Code getLifeCycleStatus() {
        return lifeCycleStatus;
    }

    public void setLifeCycleStatus(Code lifeCycleStatus) {
        this.lifeCycleStatus = lifeCycleStatus;
    }

    @JsonProperty("status_dt_tm")
    public String getStatusDtTm() {
        return statusDtTm;
    }

    public void setStatusDtTm(String statusDtTm) {
        this.statusDtTm = statusDtTm;
    }

    @JsonProperty("onset_dt_tm")
    public String getOnsetDtTm() {
        return onsetDtTm;
    }

    public void setOnsetDtTm(String onsetDtTm) {
        this.onsetDtTm = onsetDtTm;
    }

    public Code getRanking() {
        return ranking;
    }

    public void setRanking(Code ranking) {
        this.ranking = ranking;
    }

    public Code getCertainty() {
        return certainty;
    }

    public void setCertainty(Code certainty) {
        this.certainty = certainty;
    }

    @JsonProperty("individual_awareness")
     public Code getIndividualAwareness() {
        return individualAwareness;
    }

    public void setIndividualAwareness(Code individualAwareness) {
        this.individualAwareness = individualAwareness;
    }

    public Code getPrognosis() {
        return prognosis;
    }

    public void setPrognosis(Code prognosis) {
        this.prognosis = prognosis;
    }

    @JsonProperty("individual_awareness_prognosis")
     public Code getIndividualAwarenessPrognosis() {
        return individualAwarenessPrognosis;
    }

    public void setIndividualAwarenessPrognosis(Code individualAwarenessPrognosis) {
        this.individualAwarenessPrognosis = individualAwarenessPrognosis;
    }

    @JsonProperty("family_awareness")
     public Code getFamilyAwareness() {
        return familyAwareness;
    }

    public void setFamilyAwareness(Code familyAwareness) {
        this.familyAwareness = familyAwareness;
    }

    public Code getClassification() {
        return classification;
    }

    public void setClassification(Code classification) {
        this.classification = classification;
    }

    @JsonProperty("cancel_reason")
     public Code getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(Code cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Code getSeverity() {
        return severity;
    }

    public void setSeverity(Code severity) {
        this.severity = severity;
    }

    @JsonProperty("severity_class")
     public Code getSeverityClass() {
        return severityClass;
    }

    public void setSeverityClass(Code severityClass) {
        this.severityClass = severityClass;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }

    @JsonProperty("annotated_display")
    public String getAnnotatedDisplay() {
        return annotatedDisplay;
    }

    public void setAnnotatedDisplay(String annotatedDisplay) {
        this.annotatedDisplay = annotatedDisplay;
    }
}
