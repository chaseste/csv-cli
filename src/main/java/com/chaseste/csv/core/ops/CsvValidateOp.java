package com.chaseste.csv.core.ops;

import java.io.File;

import com.chaseste.csv.config.ApplicationProperties;
import com.chaseste.csv.core.io.writer.OutputWriters;
import com.chaseste.csv.core.validate.Validators;

public class CsvValidateOp extends CsvFilesOp {
    private final Validators validators;

    protected CsvValidateOp(ApplicationProperties properties, OutputWriters writers, Validators validators, 
        String modelAlias) {
        super(properties, writers, modelAlias);
        this.validators = validators;
    }

    @Override
    protected void perform(File src, File dest, File invalidRecords, File errorLog) {
        validators.csvValidator(model).requiredFields(src, dest, invalidRecords, errorLog);
    }
}
