package com.chaseste.csv.core.io.writer;

import static com.chaseste.csv.core.io.IO.BUFFER_SIZE;
import static com.chaseste.csv.core.io.IO.UTF_8;
import static com.chaseste.csv.core.io.IO.LINE_SEPARATOR_BYTES;
import static org.apache.commons.io.FileUtils.openOutputStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;


/**
 * String {@line OutputWriter}
 */
public class StringWriter extends AbstractOutputWriter<OutputStream, String> {

    public StringWriter(File dest) throws IOException {
        super(dest);
    }

    @Override
    protected OutputStream newOutput(File dest) throws IOException {
        return new BufferedOutputStream(openOutputStream(dest), BUFFER_SIZE);
    }

    @Override
    protected void write(OutputStream stream, String line) throws IOException {
        stream.write(line.getBytes(UTF_8));
        stream.write(LINE_SEPARATOR_BYTES);
    }
}
