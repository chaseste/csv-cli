package com.chaseste.csv.core.transform;

import com.chaseste.csv.core.io.writer.OutputWriters;
import com.chaseste.csv.core.mapper.Mappers;
import com.chaseste.csv.core.mapper.Models;

public class Transformers {
    private final OutputWriters writers;
    private final Mappers mappers;

    public Transformers(OutputWriters writers, Mappers mappers) {
        this.writers = writers;
        this.mappers = mappers;
    }

    /**
     * Constructs a {@link JsonTransformer} for the specified model
     * 
     * @param model the model to transform the CSV. May not be {@code null}
     * @return the {@link JsonTransformer}. Will not be {@code null}
     */
    public JsonTransformer jsonTransformer(Models model) {
        return new JsonTransformer(writers, mappers.mapper(model));
    }

    /**
     * Constructs a {@link SplitTransformer}
     * 
     * @return the {@link SplitTransformer}. Will not be {@code null}
     */
    public SplitTransformer splitTransformer() {
        return new SplitTransformer(writers);
    }
}
