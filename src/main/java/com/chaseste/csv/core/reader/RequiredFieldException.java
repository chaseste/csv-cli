package com.chaseste.csv.core.reader;

/**
 * Indicates a required field is missing
 */
public class RequiredFieldException extends InvalidFieldException {
    private static final long serialVersionUID = -5842783653577172902L;

    /**
     * Constructs a {@link RequiredFieldException}
     *  
     * @param field the missing field (zero based)  
     */
    public RequiredFieldException(int field) {
        super(String.format("Missing required field [%s].", field + 1));
    }
}