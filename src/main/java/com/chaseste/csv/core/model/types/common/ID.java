package com.chaseste.csv.core.model.types.common;

import java.util.LinkedList;
import java.util.List;

import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "authority", "type" })
public class ID {

    public static List<ID> requiredIds(List<List<String>> idFields) {
        List<ID> ids = new LinkedList<ID>();
        for (List<String> fields : idFields) {
            ID id = new ID();
            id.id = Fields.requiredString(fields, 0);
            id.authority = Fields.optionalString(fields, 1);
            id.type = Fields.optionalString(fields, 2);    
            ids.add(id);
        }
        return ids;
    }

    protected String id;
    protected String authority;
    protected String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

     public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}