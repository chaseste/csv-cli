package com.chaseste.csv.core.validate;

import static com.chaseste.csv.core.reader.CsvReader.newReader;
import static com.chaseste.csv.core.io.IO.toFile;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

import com.chaseste.csv.core.io.writer.OutputWriter;
import com.chaseste.csv.core.io.writer.OutputWriters;
import com.chaseste.csv.core.mapper.ModelMapper;
import com.chaseste.csv.core.reader.CsvReader;
import com.chaseste.csv.core.reader.CsvRecord;
import com.chaseste.csv.core.reader.InvalidFieldException;

/**
 * Csv Validator
 */
public class CsvValidator {
    private final OutputWriters writers;
    private final ModelMapper<?> mapper;

    protected CsvValidator(OutputWriters writers, ModelMapper<?> mapper) {
        this.writers = writers;
        this.mapper = mapper;
    }

    /**
     * Validates the CSV records contain the required fields
     * 
     * <p>
     * Valid records will be written to the dest file
     * 
     * <p>
     * Invalid records will be written to the error file
     * 
     * @param src            the src file. May not be {@code null}
     * @param dest           the dest file. May not be {@code null}
     * @param invalidRecords the invalid records file. May not be {@code null}
     * @param errorLog       error log file. May not be {@code null}}
     */
    public void requiredFields(File src, File dest, File invalidRecords, File errorLog) {
        try (OutputWriter<CsvRecord> csvWriter = writers.csvWriter(dest)) {
            try (CsvReader reader = newReader(src)) {
                reader.stream().forEach(record -> {
                    try {
                        mapper.map(record.fields());
                        csvWriter.write(record);
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
