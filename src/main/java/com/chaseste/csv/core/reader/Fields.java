package com.chaseste.csv.core.reader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CSV Fields
 */
public class Fields {

    /**
     * Constructs a new {@link Fields}
     * 
     * @param fields the CSV fields. May not be {@code null}
     * @return the {@link Fields}
     */
    public static Fields newFields(List<List<List<String>>> fields) {
        return new Fields(fields);
    }

    private final List<List<List<String>>> fields;

    private Fields(List<List<List<String>>> fields) {
        this.fields = fields;
    }

    /**
     * A required repeating Field
     * 
     * @param index the index of the field (zero based)
     * @return the {@link List}. Will not be {@code null} 
     */
    public List<List<String>> requiredRepeatField(int index) {
        return requiredList(fields, index);
    }

    /**
     * An optional repeating Field
     * 
     * @param index the index of the field (zero based)
     * @return the {@link List}. Will not be {@code null} 
     */
    public List<List<String>> optionalRepeatField(int index) {
        return optionalList(fields, index);
    }

    /**
     * A non repeating Field
     * 
     * @param index the index of the field (zero based)
     * @return the {@link List}. Will not be {@code null} 
     */
    public List<String> requiredField(int index) {
        return requiredList(requiredList(fields, index), 0);
    }

    /**
     * A non repeating Field
     * 
     * @param index the index of the field (zero based)
     * @return the {@link List}. Will not be {@code null} 
     */
    public List<String> optionalField(int index) {
        return optionalList(optionalList(fields, index), 0);
    }

    /**
     * A required string field
     * 
     * @param index the index of the field (zero based)
     * @return the field value. Will not be {@code null} 
     */
    public String requiredString(int index) {
        return requiredString(optionalField(index), 0);
    }

    /**
     * An optional string field
     * 
     * @param index the index of the field (zero based)
     * @return the field value. May be {@code null} 
     */
    public String optionalString(int index) {
        return optionalString(optionalField(index), 0);
    }

    /**
     * The subfields within a field
     * 
     * @param fields the fields. May not be {@code null} 
     * @param start the starting index (zero based)
     * @return the subfields. Will not be {@code null} 
     */
    public static List<String> subFields(List<String> fields, int start) {
        return subFields(fields, start, fields.size());
    }

    /**
     * The subfields within a field
     * 
     * @param fields the fields. May not be {@code null} 
     * @param start the starting index (zero based)
     * @param end the ending index (zero based)
     * @return the subfields. Will not be {@code null} 
     */
    public static List<String> subFields(List<String> fields, int start, int end) {
        List<String> subfields = new ArrayList<>(end - start + 1);
        int index = 0;
        for (String field : fields) {
            if (index >= start && index <= end) {
                subfields.add(field);
            }
            else if (index > end) {
                break;
            }
            index++;
        }
        return subfields;
    }

    /**
     * A required subfield
     * 
     * @param fields the fields. May not be {@code null}
     * @param index the index
     * @return the field value. Will not be {@code null}
     */
    public static String requiredString(List<String> fields, int index) {
        String val = required(fields, index);
        if (val.isEmpty()) {
            throw new RequiredFieldException(index);
        }
        return val;
    }

    /**
     * An optional subfield
     * 
     * @param fields the fields. May not be {@code null}
     * @param index the index
     * @return the field value. May be {@code null}
     */
    public static String optionalString(List<String> fields, int index) {
        return optional(fields, index, null);
    }

    private static <T> List<T> requiredList(List<List<T>> list, int index) {
        List<T> val = required(list, index);
        if (val.isEmpty()) {
            throw new RequiredFieldException(index);
        }
        return val;
    }

    private static <T> List<T> optionalList(List<List<T>> list, int index) {
        return optional(list, index, Collections.emptyList());
    }

    private static <T> T required(List<T> list, int index) {
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException ex) {
            throw new RequiredFieldException(index);
        }
    }

    private static <T> T optional(List<T> list, int index, T defaultVal) {
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException ex) {
            return defaultVal;
        }
    }
}
