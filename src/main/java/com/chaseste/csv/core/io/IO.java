package com.chaseste.csv.core.io;

import static java.lang.String.format;
import static java.nio.file.Files.newDirectoryStream;
import static org.apache.commons.io.FileUtils.openOutputStream;
import static org.apache.commons.io.FileUtils.deleteQuietly;
import static org.apache.commons.io.IOUtils.write;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.zip.ZipFile;

import com.chaseste.csv.core.reader.CsvRecord;

import org.apache.commons.io.Charsets;

/**
 * IO Utilities
 */
public class IO {

    /**
     * The UTF_8 {@link Charset}
     */
    public static final Charset UTF_8 = Charsets.toCharset(StandardCharsets.UTF_8);

    /**
     * The line separator bytes in UTF_8
     */
    public static final byte[] LINE_SEPARATOR_BYTES = "\n".getBytes(UTF_8);

    /**
     * The standard buffer size
     */
    public static final int BUFFER_SIZE = 24 * 1024; // 24KB

    /**
     * The working out directory
     */
    public static final String WORKING_OUT_DIR = "build/working/out";
    
    /**
     * The working in directory
     */
    public static final String WORKING_IN_DIR = "build/working/in";

    /**
     * Constructs a {@link PathMatcher} for the extension
     * 
     * @param ext the file extension. May not be {@code null}
     * @return the matcher. Will not be {@code null}
     */
    public static PathMatcher pathMatcher(String ext) {
        if (!ext.startsWith(".")) {
            throw new IllegalArgumentException(format("Invalid extension [%s]", ext));
        }
        return FileSystems.getDefault().getPathMatcher("glob:**" + ext);
    }

    /**
     * Writes / appends a {@link Exception} to the specified {@link File}
     * 
     * @param file   the file. May not be {@code null}
     * @param record the record. May not be {@code null}
     * @param ex     the error. May not be {@code null}
     */
    public static void toFile(File file, CsvRecord record, Exception ex) {
        try (OutputStream stream = new BufferedOutputStream(openOutputStream(file, true), BUFFER_SIZE)) {
            write(format("Invalid Record: Sequence [%s], error: [%s]", trimToEmpty(record.fields().optionalString(0)),
                    ex.getLocalizedMessage()), stream, UTF_8);
            write(LINE_SEPARATOR_BYTES, stream);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Writes / appends a {@link CsvRecord} to the specified {@link File}
     * 
     * @param file   the file. May not be {@code null}
     * @param record the record. May not be {@code null}
     */
    public static void toFile(File file, CsvRecord record) {
        try (OutputStream stream = new BufferedOutputStream(openOutputStream(file, true), BUFFER_SIZE)) {
            write(record.line(), stream, UTF_8);
            write(LINE_SEPARATOR_BYTES, stream);    
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Deletes the file
     * 
     * @param file the file to delete. May not be {@code null}
     */
    public static void delete(File file) {
        if (!deleteQuietly(file)) {
            file.deleteOnExit();
        }
    }

    /**
     * Deletes the spefied {@link File} if its empty
     * 
     * @param file the file. May not be {@code null}
     */
    public static void deleteIfEmpty(File file) {
        boolean deleteFile = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            deleteFile = isEmpty(reader.readLine());
        } catch (IOException e) {
            // suppress..
        } finally {
            if (deleteFile) {
                delete(file);
            }
        }
    }

    /**
     * Deletes the spefied archive {@link File} if its empty
     * 
     * @param file the file. May not be {@code null}
     */
    public static void deleteIfArchiveEmpty(File file) {
        boolean deleteFile = false;
        try (ZipFile archive = new ZipFile(file)) {
            deleteFile = archive.size() == 0;
        } catch (IOException e) {
            // suppress
        } finally {
            if (deleteFile) {
                delete(file);
            }
        }
    }

    /**
     * Checks if the path is empty
     * 
     * @param directory the directory. May not be {@code null}
     * @return {@code true } if empty else {@code false}
     */
    public static boolean isDirEmpty(final Path directory) {
        try (DirectoryStream<Path> dirStream = newDirectoryStream(directory)) {
            return !dirStream.iterator().hasNext();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private IO() {
    }
}
