package com.chaseste.csv.core.io.writer;

import static org.apache.commons.io.FileUtils.openInputStream;
import static org.apache.commons.io.FileUtils.openOutputStream;
import static org.apache.commons.io.IOUtils.copyLarge;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Zip {@line OutputWriter}
 */
public class ZipWriter extends AbstractOutputWriter<ZipOutputStream, File> {

    public ZipWriter(File dest) throws IOException {
        super(dest);
    }

    @Override
    protected ZipOutputStream newOutput(File dest) throws IOException {
        return new ZipOutputStream(new BufferedOutputStream(openOutputStream(dest)));
    }

    @Override
    protected void write(ZipOutputStream zipStream, File file) throws IOException {
        try {
            zipStream.putNextEntry(new ZipEntry(file.getName()));
            try (FileInputStream in = openInputStream(file)) {
                copyLarge(in, zipStream);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
