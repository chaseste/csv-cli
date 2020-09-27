package com.chaseste.csv.core.reader;

import static com.chaseste.csv.core.reader.Fields.newFields;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

import org.apache.commons.lang3.tuple.Pair;

/**
 * A simple CSV parser specific to our delimiters
 */
public class CsvReader implements Closeable {

    /**
     * Constructs a new {@link CsvReader}
     * 
     * @param csvFile the CSV {@link File} to read. May not be {@code null}
     * @return {@link CsvReader}
     * @throws IOException if an error occured while constructing the reader
     */
    public static CsvReader newReader(File csvFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        try {
            reader.mark(4);

            final char[] buffer = new char[4];
            reader.read(buffer);

            if ("SEQ|".equalsIgnoreCase(String.valueOf(buffer))) {
                reader.readLine();
            } else {
                reader.reset();
            }
            return new CsvReader(reader);
        } catch (IOException e) {
            try {
                reader.close();
            } catch (IOException ex) {
                // suppress so the original error isn't lost..
            }
            throw e;
        }
    }

    private final BufferedReader reader;

    private CsvReader(BufferedReader reader) throws IOException {
        this.reader = reader;
    }

    private static String stripQuotes(String line) {
        if (line.startsWith("\"") && line.endsWith("\"")) {
            return line.substring(1, line.length() - 1).replace("\"\"", "\"");
        }
        return line;
    }

    private static Stream<String> splitEscaped(String line, char delim) {
        boolean escape = false;
        int i = 0, pos = 0;

        List<String> fields = new LinkedList<String>();
        for (char c : line.toCharArray()) {
            if (c == '\"') {
                escape = !escape;
            } else if (c == delim && !escape) {
                fields.add(line.substring(pos, i));
                pos = i + 1;
            }
            i++;
        }

        if (pos < i) {
            fields.add(line.substring(pos));
        }
        return fields.stream();
    }

    private static List<List<String>> parseSubFields(String subField) {
        return splitEscaped(subField, '~').map(components -> {
            return splitEscaped(components, '|').map(CsvReader::stripQuotes).collect(Collectors.toList());
        }).collect(Collectors.toList());
    }

    /**
     * Returns a {@code Stream} of {@link Pair}s for each row in the CSV file
     * 
     * @return the pairs. Will not be {@code null}
     */
    public Stream<CsvRecord> stream() {
        return reader.lines()
                // Stage 1 - Parse the fields for each row / line
                .map(l -> Pair.of(l, splitEscaped(l, ',')))
                // Stage 2 - Extract the subfields for each field in the row
                .map(p -> Pair.of(p.getLeft(),
                        p.getRight().map(CsvReader::parseSubFields).collect(Collectors.toList())))
                // Stage 3 - Collect the fields for the line
                .map(p -> CsvRecord.of(p.getLeft(), newFields(p.getRight())));
    }

    /**
     * Closes the reader
     * 
     * @throws IOException if an error occurred
     */
    @Override
    public void close() throws IOException {
        reader.close();
    }
}