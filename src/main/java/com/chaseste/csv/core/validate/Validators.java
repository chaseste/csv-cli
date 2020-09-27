package com.chaseste.csv.core.validate;

import com.chaseste.csv.core.io.writer.OutputWriters;
import com.chaseste.csv.core.mapper.Mappers;
import com.chaseste.csv.core.mapper.Models;

public class Validators {
    private final OutputWriters writers;
    private final Mappers mappers;

    public Validators(OutputWriters writers, Mappers mappers) {
        this.writers = writers;
        this.mappers = mappers;
    }

    /**
     * Constructs a {@link CsvValidator} for the specified model
     * 
     * @param model the model to validate against. May not be {@code null}
     * @return the {@link CsvValidator}. Will not be {@code null}
     */
    public CsvValidator csvValidator(Models model) {
        return new CsvValidator(writers, mappers.mapper(model));
    }
}
