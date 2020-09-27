package com.chaseste.csv.cli;

import static com.chaseste.csv.core.io.IO.WORKING_IN_DIR;
import static com.chaseste.csv.core.io.IO.WORKING_OUT_DIR;

import com.chaseste.csv.core.ops.Ops;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * Validate Command
 */
@ShellComponent
public class Validation {
    private final Ops ops;

    public Validation(Ops ops) {
        this.ops = ops;
    }

    /**
     * Validates the files from the src
     * 
     * @param model   the model. Required
     * @param srcDir  the source directory. Optional
     * @param destDir the destination directory. Optional
     */
    @ShellMethod(value = "Validates the files from the src to the dest.", key = "validate")
    public void validate(@ShellOption(help = "The alias for the model domain / entity.") final String modelAlias,
        @ShellOption(defaultValue = WORKING_IN_DIR, help = "CSV files to validate.") final String srcDir,
        @ShellOption(defaultValue = WORKING_OUT_DIR, help = "Validated CSV files.") final String destDir) {
        ops.validateCsv(srcDir, destDir, modelAlias);
    }
}
