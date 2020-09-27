package com.chaseste.csv.core.io.writer;

import java.io.File;
import java.io.IOException;

import com.chaseste.csv.config.ApplicationProperties;
import com.chaseste.csv.core.reader.CsvRecord;

/**
 * {@link OutputWriter} factory
 */
public class OutputWriters {
    private final ApplicationProperties properties;

    /**
     * Constructor
     * 
     * @param properties the application properties. May not be {@code null}
     */
    public OutputWriters(ApplicationProperties properties) {
        this.properties = properties;
    }

    /**
     * Constructs a new Json output writer
     * 
     * @param dest the destination. May not be {@code null}
     * @return the writter. Will not be {@code null}
     * 
     * @throws IOException if an error occured
     */
    public OutputWriter<Object> jsonWriter(File dest) throws IOException {
        return isJsonSplittingEnabled() ? new SplitterWriter<Object>(JsonWriter.class, dest, recordsPerFile()) :
            new JsonWriter(dest);
    }

    /**
     * Constructs a new Csv output writer
     * 
     * @param dest the destination. May not be {@code null}
     * @return the writter. Will not be {@code null}
     * 
     * @throws IOException if an error occured
     */
    public OutputWriter<CsvRecord> csvWriter(File dest) throws IOException {
        return isCsvSplittingEnabled() ? new SplitterWriter<CsvRecord>(CsvRecordWriter.class, dest, rowsPerFile()) :
            new CsvRecordWriter(dest);
    }

    /**
     * Constructs a new archive output writer
     * 
     * @param dest the destination. May not be {@code null}
     * @return the writter. Will not be {@code null}
     * 
     * @throws IOException if an error occured
     */
    public OutputWriter<File> archiveWriter(File dest) throws IOException {
        return isArchiveSplittingEnabled() ? new SplitterWriter<File>(ZipWriter.class, dest, entriesPerArchive()) :
            new ZipWriter(dest);
    }

    /**
     * Constructs a new String output writer
     * 
     * @param dest the destination. May not be {@code null}
     * @param linesPerFile the lines per file. Must be greater than 0
     * @return the writter. Will not be {@code null}
     * 
     * @throws IOException if an error occured
     */
    public OutputWriter<String> newStringWriter(File dest, int linesPerFile) throws IOException {
        return new SplitterWriter<String>(StringWriter.class, dest, linesPerFile);
    }

    private boolean isCsvSplittingEnabled() {
        return properties.getOutput().getCsv().isSplittingEnabled();
    }

    private int rowsPerFile() {
        return properties.getOutput().getCsv().getRowsPerFile();
    }

    private boolean isJsonSplittingEnabled() {
        return properties.getOutput().getJson().isSplittingEnabled();
    }

    private int recordsPerFile() {
        return properties.getOutput().getJson().getRecordsPerFile();
    }

    private int entriesPerArchive() {
        return properties.getOutput().getCompress().getEntriesPerArchive();
    }

    private boolean isArchiveSplittingEnabled() {
        return properties.getOutput().getCompress().isSplittingEnabled();
    }
}
