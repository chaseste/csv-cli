package com.chaseste.csv.core.io.writer;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json {@line OutputWriter}
 */
public class JsonWriter extends AbstractOutputWriter<JsonGenerator, Object> {

    private static final ObjectMapper OBJECT_MAPPER;
    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.setSerializationInclusion(Include.NON_NULL);
    }

    public JsonWriter(File dest) throws IOException {
        super(dest);
    }

    @Override
    protected JsonGenerator newOutput(File dest) throws IOException {
        JsonGenerator generator = OBJECT_MAPPER.getFactory().createGenerator(dest, JsonEncoding.UTF8);
        generator.setPrettyPrinter(new MinimalPrettyPrinter("\n"));
        return generator;
    }

    @Override
    protected void write(JsonGenerator generator, Object out) throws IOException {
        OBJECT_MAPPER.writeValue(generator, out);
    }
}