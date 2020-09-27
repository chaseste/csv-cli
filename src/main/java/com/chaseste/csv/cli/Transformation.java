package com.chaseste.csv.cli;

import static com.chaseste.csv.core.io.IO.WORKING_IN_DIR;
import static com.chaseste.csv.core.io.IO.WORKING_OUT_DIR;

import com.chaseste.csv.core.ops.Ops;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * Transform Command
 */
@ShellComponent
public class Transformation {
    private final Ops ops;

    public Transformation(Ops ops) {
        this.ops = ops;
    }

    /**
     * Transforms the files from the src to the dest
     * 
     * @param model   the model. Required
     * @param srcDir  the source directory. Required
     * @param destDir the destination directory. Required
     */
    @ShellMethod(value = "Transforms the files from the src to the dest.", key = "transform")
    public void transform(@ShellOption(help = "The alias for the model domain / entity.") final String modelAlias,
            @ShellOption(defaultValue = WORKING_IN_DIR, help = "CSV files to transform.") final String srcDir,
            @ShellOption(defaultValue = WORKING_OUT_DIR, help = "JSON files.") final String destDir) {
        ops.csvToJson(srcDir, destDir, modelAlias);
    }

    /**
     * Splits the files from the src to the dest
     * 
     * @param srcDir  the source directory. Required
     * @param destDir the destination directory. Required
     */
    @ShellMethod(value = "Splits the files from the src to the dest.", key = "split")
    public void split(@ShellOption(help = "The number of lines per file.") final int linesPerFile,
        @ShellOption(help = "File extension (.csv, .txt).") final String extension,
        @ShellOption(defaultValue = WORKING_IN_DIR, help = "Source directory.") final String srcDir,
        @ShellOption(defaultValue = WORKING_OUT_DIR, help = "Destination directory.") final String destDir) {
        ops.splitFiles(srcDir, destDir, extension, linesPerFile);
    }
}
