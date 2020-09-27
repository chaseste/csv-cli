package com.chaseste.csv.core.model.types.demographics;

import java.util.LinkedList;
import java.util.List;

import com.chaseste.csv.core.model.types.common.Code;
import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "address", "type" })
public class Email {

    public static List<Email> optionalEmailAddresses(List<List<String>> emailFields) {
        List<Email> emails = new LinkedList<Email>();
        for (List<String> fields : emailFields) {
            if (!fields.isEmpty()) {
                Email email = new Email();
                email.address = Fields.optionalString(fields, 0);
                email.type = Code.optionalCode(Fields.optionalString(fields, 1));
                emails.add(email);        
            }    
        }
        return emails;
    }

    private String address;
    private Code type;    

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Code getType() {
        return type;
    }

    public void setType(Code type) {
        this.type = type;
    }
}
