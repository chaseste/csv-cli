package com.chaseste.csv.core.model.types.demographics;

import java.util.LinkedList;
import java.util.List;

import com.chaseste.csv.core.model.types.common.Code;
import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "number", "type" })
public class Phone {

    public static List<Phone> optionalPhones(List<List<String>> phoneField) {
        List<Phone> phones = new LinkedList<Phone>();
        for (List<String> fields : phoneField) {
            if (!fields.isEmpty()) {
                Phone phone = new Phone();
                phone.number = Fields.optionalString(fields, 0);
                phone.type = Code.optionalCode(Fields.optionalString(fields, 1));
                phones.add(phone);        
            }    
        }
        return phones;
    }

    String number;
    Code type;

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the type
     */
    public Code getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Code type) {
        this.type = type;
    }
}
