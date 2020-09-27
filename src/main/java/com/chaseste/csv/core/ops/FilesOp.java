package com.chaseste.csv.core.ops;

import static com.chaseste.csv.core.io.IO.delete;
import static com.chaseste.csv.core.io.IO.deleteIfArchiveEmpty;
import static com.chaseste.csv.core.io.IO.deleteIfEmpty;
import static com.chaseste.csv.core.io.IO.isDirEmpty;
import static java.time.ZonedDateTime.now;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.time.ZoneId;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

import com.chaseste.csv.config.ApplicationProperties;
import com.chaseste.csv.core.io.writer.OutputWriter;
import com.chaseste.csv.core.io.writer.OutputWriters;

public abstract class FilesOp {
    private final ApplicationProperties properties;
    private final OutputWriters writers;
    private final PathMatcher matcher;

    protected FilesOp(ApplicationProperties properties, OutputWriters writers, PathMatcher matcher) {
        this.properties = properties;
        this.writers = writers;
        this.matcher = matcher;
    }

    /**
     * Performs the operation on the files
     * 
     * @param src the source file. Will not be {@code null}
     * @param dest the dest file. Will not be {@code null}
     */
    protected abstract void perform(File src, File dest);

    /**
     * The destination file name for the given source file 
     * 
     * @param src the source file. Will not be {@code null}
     * @return the destination file name. May not be {@code null}
     */
    protected String destFileName(File src) {
        return src.getName();
    }

    /**
     * Perfroms the operation
     * 
     * @param srcPath the source of the files. May not be {@code null}
     * @param destPath the destination. May not be {@code null}
     */
    public void perform(Path srcPath, Path destPath) {
        // 1) Ensure the source isn't empty
        if (isDirEmpty(srcPath)) {
            System.out.printf("No files were found in [%s].%n", srcPath);
            return;
        }

        // 2) Determine the destination for the output
        Path outputPath = destinationPath(destPath);

        // 3) Perform the operation on the files in the src path
        listFiles(srcPath).forEach(filePath -> {
            File srcFile = filePath.toFile();
            File destFile = outputPath.resolve(destFileName(srcFile)).toFile();
            try {
                perform(srcFile, destFile);
                delete(srcFile);
            } finally {
                deleteIfEmpty(destFile);
            }
        });

        // 4) Was output produced?
        if (isDirEmpty(outputPath)) {
            System.err.printf("No files were found in [%s].%n", srcPath);
            delete(outputPath.toFile());
            return;    
        }

        // 5) Package the output
        if (properties.getOutput().getCompress().isEnabled()) {
            compressDirectory(writers, outputPath.toFile());
        }
    }

    private Stream<Path> listFiles(Path srcDir) {
        try {
            return Files.list(srcDir).filter(path -> matcher.matches(path));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static Path destinationPath(Path dest) {
        Path destPath = dest.resolve(now(ZoneId.of("UTC")).toString().replace(":", "-"));
        try {
            FileUtils.forceMkdir(destPath.toFile());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return destPath;
    }

    private static void compressDirectory(OutputWriters writers, File dest) {
        File archiveFile = new File(dest, dest.getName() + ".zip");
        try (OutputWriter<File> zipWriter = writers.archiveWriter(archiveFile)) {
            Files.list(dest.toPath())
                    // 1) Map the paths to a file
                    .map(path -> path.toFile())
                    // 2) Filter the csv file along with any invalid records / log files
                    .filter(file -> !file.getName().endsWith(".zip") && !file.getName().contains("-err."))
                    // 3) Add the good files to the zip and remove them from the output directory
                    .forEach(file -> {
                        zipWriter.write(file);
                        delete(file);
                    });
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } finally {
            deleteIfArchiveEmpty(archiveFile);
        }
    }
}
