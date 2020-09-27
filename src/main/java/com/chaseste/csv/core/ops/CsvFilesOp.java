package com.chaseste.csv.core.ops;

import static com.chaseste.csv.core.io.IO.deleteIfEmpty;
import static com.chaseste.csv.core.io.IO.pathMatcher;

import java.io.File;
import java.nio.file.Path;

import com.chaseste.csv.config.ApplicationProperties;
import com.chaseste.csv.core.io.writer.OutputWriters;
import com.chaseste.csv.core.mapper.Models;

public abstract class CsvFilesOp extends FilesOp {
    protected final Models model;

    protected CsvFilesOp(ApplicationProperties properties, OutputWriters outputWriters, String modelAlias) {
        super(properties, outputWriters, pathMatcher(".csv"));
        this.model = Models.toModel(modelAlias);
    }

    /**
     * Performs the command on the specified file
     * 
     * @param src            the source csv file. Will not be {@code null}
     * @param dest           the destination file. Will not be {@code null}
     * @param invalidRecords the invalid records file. Will not be {@code null}
     * @param errorLog       the error log file. Will not be {@code null}
     */
    protected abstract void perform(File src, File dest, File invalidRecords, File errorLog);

    @Override
    protected void perform(File src, File dest) {
        Path destPath = dest.toPath().getParent();
        File invalidRecords = destPath.resolve(src.getName().replace(".csv", "-err.csv")).toFile();
        File errorLog = destPath.resolve(src.getName().replace(".csv", "-err.log")).toFile();
        try {
            perform(src, dest, invalidRecords, errorLog);
        } finally {
            deleteIfEmpty(errorLog);
            deleteIfEmpty(invalidRecords);
        }
    }

    @Override
    public void perform(Path srcPath, Path destPath) {
        super.perform(srcPath.resolve(model.getDirectoryName()), destPath.resolve(model.getDirectoryName()));
    }
}
