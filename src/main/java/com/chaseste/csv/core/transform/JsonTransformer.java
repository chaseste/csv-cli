package com.chaseste.csv.core.transform;

import static com.chaseste.csv.core.reader.CsvReader.newReader;
import static com.chaseste.csv.core.io.IO.toFile;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.File;

import com.chaseste.csv.core.io.writer.OutputWriter;
import com.chaseste.csv.core.io.writer.OutputWriters;
import com.chaseste.csv.core.mapper.ModelMapper;
import com.chaseste.csv.core.reader.CsvReader;
import com.chaseste.csv.core.reader.InvalidFieldException;

/**
 * Json Transformer
 */
public class JsonTransformer {
    private final OutputWriters writers;
    private final ModelMapper<?> mapper;

    protected JsonTransformer(OutputWriters writers, ModelMapper<?> mapper) {
        this.writers = writers;
        this.mapper = mapper;
    }

    /**
     * Transforms the CSV file to JSON
     * 
     * @param src            the CSV (source) file. May not be {@code null}
     * @param dest           the JSON (dest) file. May not be {@code null}
     * @param invalidRecords the invalid records file. May not be {@code null}
     * @param errorLog       error log file. May not be {@code null}
     */
    public void csvToJson(File src, File dest, File invalidRecords, File errorLog) {
        try (OutputWriter<Object> jsonWriter = writers.jsonWriter(dest)) {
            try (CsvReader reader = newReader(src)) {
                reader.stream().forEach(record -> {
                    try {
                        jsonWriter.write(mapper.map(record.fields()));
                    } catch (InvalidFieldException ex) {
                        toFile(errorLog, record, ex);
                        toFile(invalidRecords, record);
                    }
                });
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
