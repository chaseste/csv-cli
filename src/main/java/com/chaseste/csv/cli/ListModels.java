package com.chaseste.csv.cli;

import com.chaseste.csv.core.mapper.Models;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * List {@link Models} command
 */
@ShellComponent
public class ListModels {

    /**
     * Lists the supported models
     */
    @ShellMethod(value = "Lists the supported models.", key = "models")
    public void models() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n-----------------------------------------");
        builder.append("\nSupported Models:");
        builder.append("\n-----------------------------------------");
        for (Models model : Models.values()) {
            builder.append("\n");
            builder.append(model.getDisplay());
        }
        builder.append("\n");
        System.out.println(builder.toString());
    }
}
