package com.chaseste.csv.core.reader;

public class CsvRecord {

    public static CsvRecord of(String line, Fields fields) {
        return new CsvRecord(line, fields);
    }

    private final String line;
    private final Fields fields;

    private CsvRecord(String line, Fields fields) {
        this.line = line;
        this.fields = fields;
    }

    public String line() {
        return line;
    }

    public Fields fields() {
        return fields;
    }
}
