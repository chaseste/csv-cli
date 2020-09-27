package com.chaseste.csv.core.model.types.common;

import com.chaseste.csv.core.reader.InvalidFieldException;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "date", "precision" })
public class PrecisionDate {

    public static PrecisionDate newPrecisionDate(String date, String precision) {
        PrecisionDate d = new PrecisionDate();
        d.date = date;
        d.precision = DatePrecision.toDatePrecision(precision);
        return d;
    }

    public enum DatePrecision {
        FULL(0), DAY(1), WEEK(2), MONTH(3), YEAR(4);

        private short val;
        
        private DatePrecision(int val) {
            this.val = (short) val;
        }

        static DatePrecision toDatePrecision(String val) {
            try {
                short precVal = Short.valueOf(val);
                for (DatePrecision p : values()) {
                    if (p.val == precVal) {
                        return p;
                    }
                }
                throw new InvalidFieldException(String.format("Invalid date precision [%s].", val));
            } catch (NumberFormatException e) {
                throw new InvalidFieldException(e);
            }
        }
    }
    
    private String date;
    private DatePrecision precision;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DatePrecision getPrecision() {
        return precision;
    }

    public void setPrecision(DatePrecision precision) {
        this.precision = precision;
    }
}
