package com.chaseste.csv.core.io.writer;

import static java.lang.String.format;
import static org.apache.commons.io.FilenameUtils.getBaseName;
import static org.apache.commons.io.FilenameUtils.getExtension;
import static org.apache.commons.io.FilenameUtils.getPath;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Splitter {@link OutputWriter}
 * 
 * <p>
 * Splits the output to 1..* files depending on how many lines / records may be in the file and the total
 * number of invocations made by the caller to write data
 */
public class SplitterWriter<T> implements OutputWriter<T> {

    private final Class<? extends OutputWriter<T>> writerClazz;
    private final int outputPerFile;
    private final File dest;
    private final Path path;

    private OutputWriter<T> writer;
    private int fileCnt;
    private int lineCnt;

    /**
     * Constructor 
     * 
     * @param writerClazz the underlying writer to persist output. May not be {@code null}
     * @param dest the destination. May not be {@code null}
     * @param outputPerFile the maximum number of lines / records in the output. Must be greater than 0
     */
    public SplitterWriter(Class<? extends OutputWriter<T>> writerClazz, File dest, 
                          int outputPerFile) {
        if (outputPerFile <= 0) {
            throw new IllegalArgumentException();
        }
        this.writerClazz = writerClazz;
        this.dest = dest;
        this.outputPerFile = outputPerFile;
        path = Paths.get(getPath(dest.getPath()));
    }

    @Override
    public void write(T out) {
        try {     
            if (lineCnt % outputPerFile == 0) {
                close();
                writer = newWriter(nextFile());
            }
            writer.write(out);
            lineCnt++;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void close() throws IOException {
        try {
            writer.close();
        } catch (NullPointerException ex) {
            // suppress to avoid having to check on every invocation if the writer isn't null..
        }
    }

    private OutputWriter<T> newWriter(File dest) {
        try {
            return writerClazz.getConstructor(File.class).newInstance(dest);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new AssertionError(e);
        }
    }

    private File nextFile() {
        String ext = getExtension(dest.getName());
        if (!ext.isEmpty()) {
            ext = "." + ext;
        }
        return path.resolve(format("%s_%d%s", getBaseName(dest.getName()), ++fileCnt, ext)).toFile();
    }
}