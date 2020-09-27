package com.chaseste.csv.core.model.types.common;

import static com.chaseste.csv.core.reader.Fields.optionalString;
import static com.chaseste.csv.core.reader.Fields.requiredString;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "type" })
public class Physician {

    public static List<Physician> optionalPhysicians(List<List<String>> physicianFields) {
        List<Physician> physicians = new LinkedList<Physician>();
        for (List<String> fields : physicianFields) {
            if (!fields.isEmpty()) {
                physicians.add(requriedPhysician(fields));
            }
        }
        return physicians;
    }

    public static Physician optionalPhysician(List<String> fields) {
        if (fields.isEmpty()) {
            return null;
        }
        return requriedPhysician(fields);
    }

    public static Physician requriedPhysician(List<String> fields) {
        Physician physician = new Physician();
        physician.type = optionalString(fields, 3);

        BasicName name = new BasicName();
        name.setLastName(requiredString(fields, 1));
        name.setFirstName(optionalString(fields, 2));
        physician.name = name;

        ID id = new ID();
        id.setId(requiredString(fields, 0));
        id.setAuthority(optionalString(fields, 4));
        physician.id = id;
        return physician;
    }

    public static Physician genericPhysician(List<String> fields) {
        Physician physician = new Physician();

        BasicName name = new BasicName();
        name.setLastName(requiredString(fields, 1));
        name.setFirstName(optionalString(fields, 2));
        physician.name = name;

        ID id = new ID();
        id.setId(requiredString(fields, 0));
        id.setAuthority(optionalString(fields, 3));
        physician.id = id;
        return physician;
    }

    private ID id;
    private BasicName name;
    private String type;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public BasicName getName() {
        return name;
    }

    public void setName(BasicName name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
