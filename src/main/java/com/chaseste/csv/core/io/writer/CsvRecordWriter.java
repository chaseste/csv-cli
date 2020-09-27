package com.chaseste.csv.core.io.writer;

import static com.chaseste.csv.core.io.IO.BUFFER_SIZE;
import static com.chaseste.csv.core.io.IO.LINE_SEPARATOR_BYTES;
import static org.apache.commons.io.FileUtils.openOutputStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import com.chaseste.csv.core.io.IO;
import com.chaseste.csv.core.reader.CsvRecord;

/**
 * CsvRecord {@line OutputWriter}
 */
public class CsvRecordWriter extends AbstractOutputWriter<OutputStream, CsvRecord> {

    public CsvRecordWriter(File dest) throws IOException {
        super(dest);
    }

    @Override
    protected OutputStream newOutput(File dest) throws IOException {
        return new BufferedOutputStream(openOutputStream(dest), BUFFER_SIZE);
    }

    @Override
    protected void write(OutputStream stream, CsvRecord record) throws IOException {
        stream.write(record.line().getBytes(IO.UTF_8));
        stream.write(LINE_SEPARATOR_BYTES);
    }
}