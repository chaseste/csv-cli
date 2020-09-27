package com.chaseste.csv.core.ops;

import java.nio.file.Paths;

import com.chaseste.csv.config.ApplicationProperties;
import com.chaseste.csv.core.io.writer.OutputWriters;
import com.chaseste.csv.core.transform.Transformers;
import com.chaseste.csv.core.validate.Validators;

public class Ops {
    private final ApplicationProperties properties;
    private final Transformers transformers;
    private final Validators validators;
    private final OutputWriters writers;

    public Ops(ApplicationProperties properties, Transformers transformers, Validators validators, 
        OutputWriters writers) {
        this.properties = properties;
        this.transformers = transformers;
        this.validators = validators;
        this.writers = writers;
    }

    public void validateCsv(String src, String dest, String modelAlias) {
        new CsvValidateOp(properties, writers, validators, modelAlias)
            .perform(Paths.get(src), Paths.get(dest));
    } 

    public void csvToJson(String src, String dest, String modelAlias) {
        new JsonTransformOp(properties, writers, transformers, modelAlias)
            .perform(Paths.get(src), Paths.get(dest));
    }

    public void splitFiles(String src, String dest, String ext, int linesPerFile) {
        new SplitFilesOp(properties, writers, transformers, ext, linesPerFile)
            .perform(Paths.get(src), Paths.get(dest));
    }
}
