package com.chaseste.csv.core.model;

import static com.chaseste.csv.core.model.types.common.Code.optionalCode;
import static com.chaseste.csv.core.model.types.common.Code.optionalCodes;
import static com.chaseste.csv.core.model.types.common.Code.requiredCode;
import static com.chaseste.csv.core.model.types.common.ID.requiredIds;
import static com.chaseste.csv.core.model.types.common.Physician.optionalPhysicians;
import static com.chaseste.csv.core.model.types.demographics.Address.optionalAddresses;
import static com.chaseste.csv.core.model.types.demographics.Email.optionalEmailAddresses;
import static com.chaseste.csv.core.model.types.demographics.FullName.optionalNames;
import static com.chaseste.csv.core.model.types.demographics.HistoricalID.requiredHistoricalIds;
import static com.chaseste.csv.core.model.types.demographics.Phone.optionalPhones;

import java.util.List;

import com.chaseste.csv.core.model.types.common.Code;
import com.chaseste.csv.core.model.types.common.Physician;
import com.chaseste.csv.core.model.types.demographics.Address;
import com.chaseste.csv.core.model.types.demographics.Email;
import com.chaseste.csv.core.model.types.demographics.FullName;
import com.chaseste.csv.core.model.types.demographics.HistoricalID;
import com.chaseste.csv.core.model.types.demographics.Phone;
import com.chaseste.csv.core.model.types.common.BasicPatient;
import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "ids", "historical_ids", "names", "birth_date", "admin_sex", "birth_sex", "race", "ethnicity",
        "mothers_maiden_name", "addresses", "phone_numbers", "language", "marital_status", "religion", "death_dt_tm",
        "deceased", "vip", "physicians", "student", "living_will", "email_addresses", "update_dt_tm" })
public class Patient extends BasicPatient {

    public static Patient newPatient(Fields fields) {
        Patient patient = new Patient();
        patient.ids = requiredIds(fields.requiredRepeatField(1));
        patient.historicalIds = requiredHistoricalIds(fields.requiredRepeatField(2));
        patient.names = optionalNames(fields.requiredRepeatField(3));
        patient.birthDate = fields.requiredString(4);
        patient.adminSex = requiredCode(fields.optionalField(5));
        patient.birthSex = optionalCode(fields.optionalField(6));
        patient.race = optionalCodes(fields.optionalRepeatField(7));
        patient.ethnicity = optionalCodes(fields.optionalRepeatField(8));
        patient.motherMaidenName = fields.optionalString(9);
        patient.addresses = optionalAddresses(fields.optionalRepeatField(10));
        patient.phoneNumbers = optionalPhones(fields.optionalRepeatField(11));
        patient.language = optionalCode(fields.optionalField(12));
        patient.maritalStatus = optionalCode(fields.optionalField(13));
        patient.religion = optionalCode(fields.optionalField(14));
        patient.deathDtTm = fields.optionalString(15);
        patient.deceased = optionalCode(fields.optionalField(16));
        patient.vip = optionalCode(fields.optionalField(17));
        patient.physicians = optionalPhysicians(fields.optionalRepeatField(18));
        patient.student = optionalCode(fields.optionalField(19));
        patient.livingWill = optionalCode(fields.optionalField(20));
        patient.emailAddresses = optionalEmailAddresses(fields.optionalRepeatField(21));
        patient.updateDtTm = fields.optionalString(22);
        return patient;
    }

    private List<HistoricalID> historicalIds;
    private List<FullName> names;
    private Code birthSex;
    private List<Code> race;
    private List<Code> ethnicity;
    private String motherMaidenName;
    private List<Address> addresses;
    private List<Phone> phoneNumbers;
    private Code language;
    private Code maritalStatus;
    private Code religion;
    private String deathDtTm;
    private Code deceased;
    private Code vip;
    private List<Physician> physicians;
    private Code student;
    private Code livingWill;
    private List<Email> emailAddresses;
    private String updateDtTm;

    @JsonProperty("historical_ids")
    public List<HistoricalID> getHistoricalIds() {
        return historicalIds;
    }

    public void setHistoricalIds(List<HistoricalID> historicalIds) {
        this.historicalIds = historicalIds;
    }

    public List<FullName> getNames() {
        return names;
    }

    public void setNames(List<FullName> names) {
        this.names = names;
    }

    @JsonProperty("birth_sex")
    public Code getBirthSex() {
        return birthSex;
    }

    public void setBirthSex(Code birthSex) {
        this.birthSex = birthSex;
    }

    public List<Code> getRace() {
        return race;
    }

    public void setRace(List<Code> race) {
        this.race = race;
    }

    public List<Code> getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(List<Code> ethnicity) {
        this.ethnicity = ethnicity;
    }

    @JsonProperty("mothers_maiden_name")
    public String getMotherMaidenName() {
        return motherMaidenName;
    }

    public void setMotherMaidenName(String motherMaidenName) {
        this.motherMaidenName = motherMaidenName;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @JsonProperty("phone_numbers")
    public List<Phone> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<Phone> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Code getLanguage() {
        return language;
    }

    public void setLanguage(Code language) {
        this.language = language;
    }

    @JsonProperty("marital_status")
    public Code getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Code maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Code getReligion() {
        return religion;
    }

    public void setReligion(Code religion) {
        this.religion = religion;
    }

    @JsonProperty("death_dt_tm")
    public String getDeathDtTm() {
        return deathDtTm;
    }

    public void setDeathDtTm(String deathDtTm) {
        this.deathDtTm = deathDtTm;
    }

    public Code getDeceased() {
        return deceased;
    }

    public void setDeceased(Code deceased) {
        this.deceased = deceased;
    }

    public Code getVip() {
        return vip;
    }

    public void setVip(Code vip) {
        this.vip = vip;
    }

    public List<Physician> getPhysicians() {
        return physicians;
    }

    public void setPhysicians(List<Physician> physicians) {
        this.physicians = physicians;
    }

    public Code getStudent() {
        return student;
    }

    public void setStudent(Code student) {
        this.student = student;
    }

    @JsonProperty("living_will")
    public Code getLivingWill() {
        return livingWill;
    }

    public void setLivingWill(Code livingWill) {
        this.livingWill = livingWill;
    }

    @JsonProperty("email_addresses")
    public List<Email> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(List<Email> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    @JsonProperty("update_dt_tm")
    public String getUpdateDtTm() {
        return updateDtTm;
    }

    public void setUpdateDtTm(String updateDtTm) {
        this.updateDtTm = updateDtTm;
    }
}
