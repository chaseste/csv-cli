package com.chaseste.csv.core.model.types.common;

import java.util.List;

import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BasicPatient {

    public static BasicPatient requiredBasicPatient(Fields fields) {
        BasicPatient patient = new BasicPatient();
        patient.ids = ID.requiredIds(fields.requiredRepeatField(1));
        patient.name = BasicName.requiredName(fields.optionalField(2));
        patient.birthDate = fields.requiredString(3);
        patient.adminSex = Code.requiredCode(fields.optionalField(4));
        return patient;
    }

    protected List<ID> ids;
    protected BasicName name;
    protected String birthDate;
    protected Code adminSex;

    public List<ID> getIds() {
        return ids;
    }

    public void setIds(List<ID> ids) {
        this.ids = ids;
    }

    public BasicName getName() {
        return name;
    }

    public void setName(BasicName name) {
        this.name = name;
    }

    @JsonProperty("birth_date")
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @JsonProperty("admin_sex")
    public Code getAdminSex() {
        return adminSex;
    }

    public void setAdminSex(Code adminSex) {
        this.adminSex = adminSex;
    }
}
