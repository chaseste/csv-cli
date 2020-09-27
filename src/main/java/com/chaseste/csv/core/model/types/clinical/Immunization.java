package com.chaseste.csv.core.model.types.clinical;

import static com.chaseste.csv.core.model.types.common.Code.optionalCode;
import static com.chaseste.csv.core.model.types.common.Code.requiredCode;
import static com.chaseste.csv.core.model.types.common.Note.optionalNotes;
import static com.chaseste.csv.core.model.types.common.Physician.optionalPhysicians;

import java.util.Arrays;
import java.util.List;

import com.chaseste.csv.core.model.types.common.Code;
import com.chaseste.csv.core.model.types.common.Note;
import com.chaseste.csv.core.model.types.common.Physician;
import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "unique_id", "physicians", "route", "site", "admin_dt_tm", "vaccine_code", "admin_units",
        "lot_number", "lot_expire_dt_tm", "manufacturer", "substance_refusal_reason", "completion_status",
        "admin_amount", "vis_dt_tm", "vfc_status", "notes", "historical_source" })
public class Immunization {

    public static List<Immunization> requiredImmunizations(Fields fields) {
        Immunization i = new Immunization();
        i.uniqueId = fields.requiredString(6);
        i.physicians = optionalPhysicians(fields.optionalRepeatField(7));
        i.route = optionalCode(fields.optionalField(8));
        i.site = optionalCode(fields.optionalField(9));
        i.adminDate = fields.requiredString(10);
        i.vaccineCode = requiredCode(fields.requiredField(11));
        i.adminUnits = requiredCode(fields.requiredField(12));
        i.lotNumber = fields.optionalString(13);
        i.lotExpireDate = fields.optionalString(14);
        i.manufacturer = optionalCode(fields.optionalField(15));
        i.substanceRefusalReason = optionalCode(fields.optionalField(16));
        i.completionStatus = requiredCode(fields.requiredField(17));
        i.adminAmount = fields.requiredString(18);
        i.visDate = fields.optionalString(19);
        i.vfcStatus = optionalCode(fields.optionalField(20));
        i.notes = optionalNotes(fields.optionalRepeatField(21));
        i.historicalSource = optionalCode(fields.optionalField(22));
        return Arrays.asList(i);
    }

    private String uniqueId;
    private List<Physician> physicians;
    private Code route;
    private Code site;
    private String adminDate;
    private Code vaccineCode;
    private Code adminUnits;
    private String lotNumber;
    private String lotExpireDate;
    private Code manufacturer;
    private Code substanceRefusalReason;
    private Code completionStatus;
    private String adminAmount;
    private String visDate;
    private Code vfcStatus;
    private List<Note> notes;
    private Code historicalSource;

    @JsonProperty("unique_id")
    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public List<Physician> getPhysicians() {
        return physicians;
    }

    public void setPhysicians(List<Physician> physicians) {
        this.physicians = physicians;
    }

    public Code getRoute() {
        return route;
    }

    public void setRoute(Code route) {
        this.route = route;
    }

    public Code getSite() {
        return site;
    }

    public void setSite(Code site) {
        this.site = site;
    }

    @JsonProperty("admin_dt_tm")
    public String getAdminDate() {
        return adminDate;
    }

    public void setAdminDate(String adminDate) {
        this.adminDate = adminDate;
    }

    @JsonProperty("vaccine_code")
    public Code getVaccineCode() {
        return vaccineCode;
    }

    public void setVaccineCode(Code vaccineCode) {
        this.vaccineCode = vaccineCode;
    }

    @JsonProperty("admin_units")
    public Code getAdminUnits() {
        return adminUnits;
    }

    public void setAdminUnits(Code adminUnits) {
        this.adminUnits = adminUnits;
    }

    @JsonProperty("lot_number")
    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    @JsonProperty("lot_expire_dt_tm")
    public String getLotExpireDate() {
        return lotExpireDate;
    }

    public void setLotExpireDate(String lotExpireDate) {
        this.lotExpireDate = lotExpireDate;
    }

    public Code getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Code manufacturer) {
        this.manufacturer = manufacturer;
    }

    @JsonProperty("substance_refusal_reason")
    public Code getSubstanceRefusalReason() {
        return substanceRefusalReason;
    }

    public void setSubstanceRefusalReason(Code substanceRefusalReason) {
        this.substanceRefusalReason = substanceRefusalReason;
    }

    @JsonProperty("completion_status")
    public Code getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(Code completionStatus) {
        this.completionStatus = completionStatus;
    }

    @JsonProperty("admin_amount")
    public String getAdminAmount() {
        return adminAmount;
    }

    public void setAdminAmount(String adminAmount) {
        this.adminAmount = adminAmount;
    }

    @JsonProperty("vis_dt_tm")
    public String getVisDate() {
        return visDate;
    }

    public void setVisDate(String visDate) {
        this.visDate = visDate;
    }

    @JsonProperty("vfc_status")
    public Code getVfcStatus() {
        return vfcStatus;
    }

    public void setVfcStatus(Code vfcStatus) {
        this.vfcStatus = vfcStatus;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @JsonProperty("historical_source")
    public Code getHistoricalSource() {
        return historicalSource;
    }

    public void setHistoricalSource(Code historicalSource) {
        this.historicalSource = historicalSource;
    }
}
