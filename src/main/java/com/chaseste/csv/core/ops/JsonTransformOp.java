package com.chaseste.csv.core.ops;

import java.io.File;

import com.chaseste.csv.config.ApplicationProperties;
import com.chaseste.csv.core.io.writer.OutputWriters;
import com.chaseste.csv.core.transform.Transformers;

public class JsonTransformOp extends CsvFilesOp {
    private final Transformers transformers;

    protected JsonTransformOp(ApplicationProperties properties, OutputWriters writers, Transformers transformers,
        String modelAlias) {
        super(properties, writers, modelAlias);
        this.transformers = transformers;
    }

    @Override
    protected String destFileName(File src) {
        return src.getName().replace(".csv", ".json");
    }
    
    @Override
    protected void perform(File src, File dest, File invalidRecords, File errorLog) {
        transformers.jsonTransformer(model).csvToJson(src, dest, invalidRecords, errorLog);
    }
}
