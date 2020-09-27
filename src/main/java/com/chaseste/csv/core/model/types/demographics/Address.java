package com.chaseste.csv.core.model.types.demographics;

import java.util.LinkedList;
import java.util.List;

import com.chaseste.csv.core.model.types.common.Code;
import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "line1", "line2", "city", "state", "zip_code", "country", "type" })
public class Address {

    public static List<Address> optionalAddresses(List<List<String>> addressFields) {
        List<Address> addresses = new LinkedList<Address>();
        for (List<String> fields : addressFields) {
            if (!fields.isEmpty()) {
                Address address = new Address();
                address.addressLine1 = Fields.optionalString(fields, 0);
                address.addressLine2 = Fields.optionalString(fields, 1);
                address.city = Fields.optionalString(fields, 2);
                address.state = Code.optionalCode(Fields.optionalString(fields, 3));
                address.zipCode = Fields.optionalString(fields, 4);
                address.country = Code.optionalCode(Fields.optionalString(fields, 5));
                address.type = Code.optionalCode(Fields.optionalString(fields, 6));        
                addresses.add(address);
            }    
        }
        return addresses;
    }

    private String addressLine1;
    private String addressLine2;
    private String city;
    private Code state;
    private String zipCode;
    private Code country;
    private Code type;

    @JsonProperty("line1")
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @JsonProperty("line2")
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Code getState() {
        return state;
    }

    public void setState(Code state) {
        this.state = state;
    }

    @JsonProperty("zip_code")
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Code getCountry() {
        return country;
    }

    public void setCountry(Code country) {
        this.country = country;
    }

    public Code getType() {
        return type;
    }

    public void setType(Code type) {
        this.type = type;
    }
}
