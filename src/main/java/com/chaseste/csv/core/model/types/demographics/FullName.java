package com.chaseste.csv.core.model.types.demographics;

import com.chaseste.csv.core.model.types.common.Code;
import com.chaseste.csv.core.reader.Fields;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.chaseste.csv.core.model.types.common.BasicName;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "last", "first", "middle", "suffix", "type" })
public class FullName extends BasicName {

    public static List<FullName> optionalNames(List<List<String>> nameFields) {
        List<FullName> names = new LinkedList<FullName>();
        for (List<String> fields : nameFields) {
            if (!fields.isEmpty()) {
                FullName name = new FullName();
                name.lastName = Fields.requiredString(fields, 0);
                name.firstName = Fields.requiredString(fields, 1);
                name.middleName = Fields.optionalString(fields, 2);
                name.suffix = Fields.optionalString(fields, 3);
                name.type = Code.requiredCode(Arrays.asList(Fields.requiredString(fields, 4)));        
                names.add(name);
            }    
        }
        return names;
    }

    private String suffix;
    private Code type;

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Code getType() {
        return type;
    }

    public void setType(Code type) {
        this.type = type;
    }
}