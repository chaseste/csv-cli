package com.chaseste.csv.core.reader;

/**
 * Indicates a field is invalid
 */
public class InvalidFieldException extends RuntimeException {

    private static final long serialVersionUID = 4211046300649339403L;
    
    /**
     * Constructs a {@link InvalidFieldException}
     *  
     * @param message the message.  
     */
    public InvalidFieldException(String message) {
        super(message);
    }


    /**
     * Constructs a {@link InvalidFieldException}
     *  
     * @param cause the cause.  
     */
    public InvalidFieldException(Throwable cause) {
        super(cause);
    }
}
