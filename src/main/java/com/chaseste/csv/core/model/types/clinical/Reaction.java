package com.chaseste.csv.core.model.types.clinical;

import static com.chaseste.csv.core.model.types.common.Code.optionalCode;

import java.util.LinkedList;
import java.util.List;

import com.chaseste.csv.core.model.types.common.Code;
import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "code", "severity" })
public class Reaction {

    public static List<Reaction> optionalReactions(List<List<String>> reactionFields) {
        List<Reaction> reactions = new LinkedList<Reaction>();
        for (List<String> fields : reactionFields) {
            if (!fields.isEmpty()) {
                Reaction reaction = new Reaction();
                reaction.code = optionalCode(Fields.subFields(fields, 0, 2));
                reaction.severity = optionalCode(Fields.subFields(fields, 3));
                reactions.add(reaction);
            }
        }
        return reactions;
    }

    private Code code;
    private Code severity;

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public Code getSeverity() {
        return severity;
    }

    public void setSeverity(Code severity) {
        this.severity = severity;
    }
}
