package com.chaseste.csv.core.mapper;

import com.chaseste.csv.core.reader.Fields;

/**
 * Base Model Mapper
 */
public interface ModelMapper<T> {

    /**
     * Maps the fields
     * 
     * @param fields the fields to map. Will not be {@code null}
     * @return the mapped object. May not be {@code null}
     */
    public T map(Fields fields);
}