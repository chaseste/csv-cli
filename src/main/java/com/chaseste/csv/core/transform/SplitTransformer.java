package com.chaseste.csv.core.transform;

import static com.chaseste.csv.core.io.IO.BUFFER_SIZE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;

import com.chaseste.csv.core.io.writer.OutputWriter;
import com.chaseste.csv.core.io.writer.OutputWriters;

/**
 * Split Transformer
 */
public class SplitTransformer {
    private final OutputWriters writers;

    protected SplitTransformer(OutputWriters writers) {
        this.writers = writers;
    }

    /**
     * Splits the src file into 1..* file based on the size of the file and 
     * the maximum number of lines per file
     * 
     * @param src          the source file. May not be {@code null}
     * @param dest         the dest file (pattern). May not be {@code null}
     * @param linesPerFile the desired lines per file. May not be {@code null}
     */
    public void split(File src, File dest, int linesPerFile) {
        try (OutputWriter<String> writer = writers.newStringWriter(dest, linesPerFile)) {        
            try (BufferedReader reader = new BufferedReader(new FileReader(src), BUFFER_SIZE)) {
                reader.lines().forEach(line -> {
                    writer.write(line);
                });
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
