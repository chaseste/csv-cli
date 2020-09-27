package com.chaseste.csv.core.model.types.clinical;

import java.util.List;

import com.chaseste.csv.core.model.types.common.BasicVisit;
import com.chaseste.csv.core.model.types.common.Physician;
import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.chaseste.csv.core.model.types.common.Code;
import com.chaseste.csv.core.model.types.common.ID;

import java.util.Arrays;

@JsonPropertyOrder({ 
    "ids",
    "facility", 
    "ambulatory_unit", 
    "arrive_date", 
    "discharge_date", 
    "registration_date", 
    "depart_date",
    "physicians",
    "type",
    "medical_service",
    "vip",
    "reason_for_visit",
    "admission_source",
    "discharge_location",
    "discharge_disposition",
    "status"
})
public class Visit extends BasicVisit {

    public static List<Visit> requiredVisits(Fields fields) {
        Visit visit = new Visit();
        visit.ids = ID.requiredIds(fields.requiredRepeatField(5));
        visit.facility = Code.requiredCode(fields.requiredField(6));
        visit.ambulatoryUnit = Code.requiredCode(fields.requiredField(7));
        visit.arriveDate = fields.requiredString(8);
        visit.dischargeDate = fields.requiredString(9);
        visit.registrationDate = fields.optionalString(10);
        visit.departDate = fields.optionalString(11);
        visit.physicians = Physician.optionalPhysicians(fields.optionalRepeatField(12));
        visit.type = Code.requiredCode(fields.requiredField(13));
        visit.medicalService = Code.optionalCode(fields.optionalField(14));
        visit.vip = Code.optionalCode(fields.optionalField(15));
        visit.reasonForVisit = Code.optionalCode(fields.optionalField(16));
        visit.admissionSource = Code.optionalCode(fields.optionalField(17));
        visit.dischargeLocation = Code.optionalCode(fields.optionalField(18));
        visit.dischargeDisposition = Code.optionalCode(fields.optionalField(19));
        visit.status = Code.optionalCode(fields.optionalField(20));
        return Arrays.asList(visit);
    }

    private Code facility;
    private Code ambulatoryUnit;
    private String arriveDate;
    private String dischargeDate;
    private String registrationDate;
    private String departDate;
    private List<Physician> physicians;
    private Code type;
    private Code medicalService;
    private Code vip;
    private Code reasonForVisit;
    private Code admissionSource;
    private Code dischargeLocation;
    private Code dischargeDisposition;
    private Code status;

    public Code getFacility() {
        return facility;
    }

    public void setFacility(Code facility) {
        this.facility = facility;
    }

    @JsonProperty("ambulatory_unit")
    public Code getAmbulatoryUnit() {
        return ambulatoryUnit;
    }

    public void setAmbulatoryUnit(Code ambulatoryUnit) {
        this.ambulatoryUnit = ambulatoryUnit;
    }

    @JsonProperty("arrive_date")
    public String getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(String arriveDate) {
        this.arriveDate = arriveDate;
    }

    @JsonProperty("discharge_date")
    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    @JsonProperty("registration_date")
    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    @JsonProperty("depart_date")
    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public List<Physician> getPhysicians() {
        return physicians;
    }

    public void setPhysicians(List<Physician> physicians) {
        this.physicians = physicians;
    }

    public Code getType() {
        return type;
    }

    public void setType(Code type) {
        this.type = type;
    }

    @JsonProperty("medical_service")
    public Code getMedicalService() {
        return medicalService;
    }

    public void setMedicalService(Code medicalService) {
        this.medicalService = medicalService;
    }

    public Code getVip() {
        return vip;
    }

    public void setVip(Code vip) {
        this.vip = vip;
    }

    @JsonProperty("reason_for_visit")
    public Code getReasonForVisit() {
        return reasonForVisit;
    }

    public void setReasonForVisit(Code reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }

    @JsonProperty("admission_source")
    public Code getAdmissionSource() {
        return admissionSource;
    }

    public void setAdmissionSource(Code admissionSource) {
        this.admissionSource = admissionSource;
    }

    @JsonProperty("discharge_location")
    public Code getDischargeLocation() {
        return dischargeLocation;
    }

    public void setDischargeLocation(Code dischargeLocation) {
        this.dischargeLocation = dischargeLocation;
    }

    @JsonProperty("discharge_disposition")
    public Code getDischargeDisposition() {
        return dischargeDisposition;
    }

    public void setDischargeDisposition(Code dischargeDisposition) {
        this.dischargeDisposition = dischargeDisposition;
    }

    public Code getStatus() {
        return status;
    }

    public void setStatus(Code status) {
        this.status = status;
    }
}
