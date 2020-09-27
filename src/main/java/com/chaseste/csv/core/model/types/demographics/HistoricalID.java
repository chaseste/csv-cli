package com.chaseste.csv.core.model.types.demographics;

import java.util.LinkedList;
import java.util.List;

import com.chaseste.csv.core.model.types.common.ID;
import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "authority", "id_type", "start", "end" })
public class HistoricalID extends ID {

    public static List<HistoricalID> requiredHistoricalIds(List<List<String>> idFields) {
        List<HistoricalID> ids = new LinkedList<HistoricalID>();
        for (List<String> fields : idFields) {
            HistoricalID id = new HistoricalID();
            id.id = Fields.requiredString(fields, 0);
            id.authority = Fields.optionalString(fields, 1);
            id.type = Fields.optionalString(fields, 2);
            id.start = Fields.optionalString(fields, 3);
            id.end = Fields.optionalString(fields, 4);
            ids.add(id);  
        }
        return ids;
    }

    private String start;
    private String end;

    /**
     * @return String return the start
     */
    public String getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * @return String return the end
     */
    public String getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(String end) {
        this.end = end;
    }

}
