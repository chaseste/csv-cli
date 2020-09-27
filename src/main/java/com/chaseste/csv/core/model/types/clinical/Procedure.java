package com.chaseste.csv.core.model.types.clinical;

import static com.chaseste.csv.core.model.types.clinical.Procedure.ProcedureType.toProcedureType;
import static com.chaseste.csv.core.model.types.common.Code.optionalCodes;
import static com.chaseste.csv.core.model.types.common.Code.requiredCode;
import static com.chaseste.csv.core.model.types.common.Comment.optionalComments;
import static com.chaseste.csv.core.model.types.common.Physician.optionalPhysician;
import static com.chaseste.csv.core.model.types.common.PrecisionDate.newPrecisionDate;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.util.Arrays;
import java.util.List;

import com.chaseste.csv.core.model.types.common.Code;
import com.chaseste.csv.core.model.types.common.Comment;
import com.chaseste.csv.core.model.types.common.Physician;
import com.chaseste.csv.core.model.types.common.PrecisionDate;
import com.chaseste.csv.core.reader.Fields;
import com.chaseste.csv.core.reader.InvalidFieldException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "code", "modifiers", "perform_dt_tm", "type", "physician", "comments" })
public class Procedure {

    public static List<Procedure> requiredProcedures(Fields fields) {
        Procedure p = new Procedure();
        p.code = requiredCode(fields.requiredField(6));
        p.modifiers = optionalCodes(fields.optionalRepeatField(7));
        p.performDate = newPrecisionDate(fields.requiredString(8), fields.requiredString(9));
        p.type = toProcedureType(fields.optionalString(10));
        p.physician = optionalPhysician(fields.optionalField(11));
        p.comments = optionalComments(fields.optionalRepeatField(12));
        return Arrays.asList(p);
    }

    public enum ProcedureType {
        UNKNOWN(0), ENCOUNTER(1), NARRATED(2);

        private short val;

        private ProcedureType(int val) {
            this.val = (short) val;
        }

        static ProcedureType toProcedureType(String val) {
            if (isEmpty(val)) {
                return null;
            }

            try {
                short typeVal = Short.valueOf(val);
                for (ProcedureType t : values()) {
                    if (t.val == typeVal) {
                        return t;
                    }
                }
                throw new InvalidFieldException(String.format("Invalid procedure type [%s].", val));
            } catch (NumberFormatException e) {
                throw new InvalidFieldException(e);
            }
        }
    }
    
    private Code code;
    private List<Code> modifiers;
    private PrecisionDate performDate;
    private ProcedureType type;
    private Physician physician;
    private List<Comment> comments;

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public List<Code> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<Code> modifiers) {
        this.modifiers = modifiers;
    }

    @JsonProperty("perform_dt_tm")
    public PrecisionDate getPerformDate() {
        return performDate;
    }

    public void setPerformDate(PrecisionDate performDate) {
        this.performDate = performDate;
    }

    public ProcedureType getType() {
        return type;
    }

    public void setType(ProcedureType type) {
        this.type = type;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
