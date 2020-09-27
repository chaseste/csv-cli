package com.chaseste.csv.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    private final Output output = new Output();

    public Output getOutput() {
        return output;
    }

    public static class Output {
        private final Compress compress = new Compress();
        private final Json json = new Json();
        private final Csv csv = new Csv();

        public Compress getCompress() {
            return compress;
        }

        public Json getJson() {
            return json;
        }

        public Csv getCsv() {
            return csv;
        }

        public static class Compress {
            private boolean enabled;
            private int entriesPerArchive;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public boolean isSplittingEnabled() {
                return entriesPerArchive > 0;
            }

            public int getEntriesPerArchive() {
                return entriesPerArchive;
            }

            public void setEntriesPerArchive(int entriesPerArchive) {
                if (entriesPerArchive < 1) {
                    throw new IllegalArgumentException();
                }
                this.entriesPerArchive = entriesPerArchive;
            }
        }

        public static class Json {
            private int recordsPerFile = -1;

            public boolean isSplittingEnabled() {
                return recordsPerFile > 0;
            }

            public int getRecordsPerFile() {
                return recordsPerFile;
            }

            public void setRecordsPerFile(int recordsPerFile) {
                if (recordsPerFile < 20000) {
                    throw new IllegalArgumentException();
                }
                this.recordsPerFile = recordsPerFile;
            }
        }

        public static class Csv {
            private int rowsPerFile = -1;

            public boolean isSplittingEnabled() {
                return rowsPerFile > 0;
            }

            public int getRowsPerFile() {
                return rowsPerFile;
            }

            public void setRowsPerFile(int rowsPerFile) {
                if (rowsPerFile < 20000) {
                    throw new IllegalArgumentException();
                }
                this.rowsPerFile = rowsPerFile;
            }
        }
    }
}