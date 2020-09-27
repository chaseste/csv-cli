package com.chaseste.csv.core.model.types.common;

import static com.chaseste.csv.core.model.types.common.Code.optionalCode;
import static com.chaseste.csv.core.reader.Fields.optionalString;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "text", "type" })
public class Note {

    public static List<Note> optionalNotes(List<List<String>> noteFields) {
        List<Note> notes = new LinkedList<Note>();
        for (List<String> fields : noteFields) {
            if (!fields.isEmpty()) {
                Note note = new Note();
                note.text = optionalString(fields, 0);
                note.type = optionalCode(optionalString(fields, 1));
                notes.add(note);
            }    
        }
        return notes;
    }
    
    private String text;
    private Code type;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Code getType() {
        return type;
    }

    public void setType(Code type) {
        this.type = type;
    }
}
