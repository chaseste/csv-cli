package com.chaseste.csv.core.model.types.common;

import java.util.LinkedList;
import java.util.List;

import com.chaseste.csv.core.reader.Fields;
import com.chaseste.csv.core.reader.RequiredFieldException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "description", "coding_method" })
public class Code {

    public static List<Code> optionalCodes(List<List<String>> codeFields) {
        List<Code> codes = new LinkedList<Code>();
        for (List<String> fields : codeFields) {
            if (!fields.isEmpty()) {
                codes.add(requiredCode(fields));
            }
        }
        return codes;
    }

    public static Code optionalCode(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        Code code = new Code();
        code.id = id;
        return code;
    }

    public static Code optionalCode(List<String> fields) {
        if (fields.isEmpty()) {
            return null;
        }
        return requiredCode(fields);
    }

    public static Code requiredCode(List<String> fields) {
        Code code = new Code();
        code.id = Fields.optionalString(fields, 0);
        code.description = Fields.optionalString(fields, 1);
        code.codingMethod = Fields.optionalString(fields, 2);
        if (code.id == null && code.description == null) {
            throw new RequiredFieldException(0);
        }
        return code;
    }

    private String id;
    private String description;
    private String codingMethod;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("coding_method")
    public String getCodingMethod() {
        return codingMethod;
    }

    public void setCodingMethod(String codingMethod) {
        this.codingMethod = codingMethod;
    }
}
