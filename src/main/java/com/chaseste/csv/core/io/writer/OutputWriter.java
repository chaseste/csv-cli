package com.chaseste.csv.core.io.writer;

import java.io.Closeable;

/**
 * Output writer
 */
public interface OutputWriter<Data> extends Closeable {

    /**
     * Writes the data
     * 
     * @param out the data to write. May not be {@code null}
     */
    public void write(Data out);
}