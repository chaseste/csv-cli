package com.chaseste.csv.core.ops;

import static com.chaseste.csv.core.io.IO.pathMatcher;

import java.io.File;

import com.chaseste.csv.config.ApplicationProperties;
import com.chaseste.csv.core.io.writer.OutputWriters;
import com.chaseste.csv.core.transform.Transformers;

public class SplitFilesOp extends FilesOp {
    private final Transformers transformers;
    private final int linesPerFile;

    protected SplitFilesOp(ApplicationProperties properties, OutputWriters writers, Transformers transformers,
            String ext, int linesPerFile) {
        super(properties, writers, pathMatcher(ext));
        this.transformers = transformers;
        this.linesPerFile = linesPerFile;
    }

    @Override
    protected void perform(File src, File dest) {
        transformers.splitTransformer().split(src, dest, linesPerFile);
    }
}
