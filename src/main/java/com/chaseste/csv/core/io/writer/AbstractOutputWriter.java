package com.chaseste.csv.core.io.writer;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Abstract {@link OutputWriter}
 */
public abstract class AbstractOutputWriter<Output extends Closeable, Data> implements OutputWriter<Data> {

    private final Output output;

    /**
     * Constructor
     * 
     * @param dest the destination for the output. May not be {@code null}
     * 
     * @throws IOException if an error occured
     */
    protected AbstractOutputWriter(File dest) throws IOException {
        output = newOutput(dest);
    }

    @Override
    public void write(Data data) {
        try {
            write(output, data);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void close() throws IOException {
        output.close();
    }

   /**
     * Constructs a new output for the file
     * 
     * @param dest the destination of the output. May not be {@code null}
     * @return the output. May not be {@code null}
     * 
     * @throws IOException if an error occured
     */
    protected abstract Output newOutput(File dest) throws IOException;

    /**
     * Writes the data to the output
     * 
     * @param output the output. May not be {@code null}
     * @param data the data to write. May not be {@code null}
     * 
     * @throws IOException if an error occured
     */
    protected abstract void write(Output output, Data data) throws IOException;
}