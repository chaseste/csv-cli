package com.chaseste.csv;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Csv CLI Application
 */
@SpringBootApplication
public class CsvCliApp {
    public static void main(final String[] args) {
        new SpringApplicationBuilder(CsvCliApp.class)
                .web(false)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
