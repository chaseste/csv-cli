package com.chaseste.csv.core.model.types.common;

import static com.chaseste.csv.core.model.types.common.ID.requiredIds;

import java.util.List;

import com.chaseste.csv.core.reader.Fields;

public class BasicVisit {

    public static BasicVisit requiredBasicVisit(Fields fields) {
        BasicVisit visit = new BasicVisit();
        visit.ids = requiredIds(fields.requiredRepeatField(5));
        return visit;
    }

    protected List<ID> ids;

    public List<ID> getIds() {
        return ids;
    }

    public void setIds(List<ID> ids) {
        this.ids = ids;
    }
}
