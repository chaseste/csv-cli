package com.chaseste.csv.core.model.types.common;

import java.util.List;

import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "last", "first", "middle" })
public class BasicName {

    public static BasicName requiredName(List<String> fields) {
        BasicName name = new BasicName();
        name.lastName = Fields.requiredString(fields, 0);
        name.firstName = Fields.optionalString(fields, 1);
        name.middleName = Fields.optionalString(fields, 2);
        return name;
    }

    protected String lastName;
    protected String firstName;
    protected String middleName;

    @JsonProperty("last")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("first")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("middle")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}