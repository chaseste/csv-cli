# csv-cli
CSV CLI

##### Table of Contents
- [Motivation](#motivation)
- [Requirements](#requirements)
  * [Disclaimer](#disclaimer)
- [Build](#build)
  * [Checking out the code](#checking-out-the-code)
  * [Building](#building)
    + [Build Folder](#build-folder)
      - [Default](#default)
      - [Local](#local)
  * [Usage](#usage)
    + [Disclaimer](#disclaimer-1)
  * [Configurations](#configurations)
    + [Sample applicaton.yml](#sample-applicatonyml)
  * [Directories](#directories)
  * [Commands](#commands)
    + [Models](#models)
    + [Transform](#transform)
    + [Validate](#validate)
    + [split](#split)
    + [Packaging](#packaging)
  * [System Properties](#system-properties)
    + [cli.verbose](#cliverbose)
- [Results](#results)
  * [Transform](#transform-1)
  * [Validation](#validation)
- [What's Left?](#whats-left)

# Motivation
This is a continuation of [csv_to_json](https://github.com/chaseste/csv_to_json) to explore 1.) Writing a apples to apples comparion of Java to Python, 2.) Seeing the apples to apples performance differences between Java and Python and 3.) Extending to include validation of the source CSV. Granted a apples to apples comparion is hard though a best effort was made.

# Requirements
- [Maven](https://maven.apache.org/download.cgi)
- [JDK8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

## Disclaimer
The current dependencies won't work on JDK 15. JDK 8 is what was tested against. JDK 8+ should be possible with some additional dependency tweaks (most noteably jcommander).

# Build
## Checking out the code
The code can either be checked out via git or downloaded as a zip file and extracted to your system.

```
git clone https://github.com/chaseste/csv-cli.git
```

## Building
The project provides a build script for Windows. The script supports both a global and local environment. The default is global. When local is used the script will install both Java and Maven locally in the build directory. Global expects both Maven / Java to be installed and on the path. 

| Command | Action |
| --- | --- |
| build | Default action. Builds the CLI using the global installs |
| build package | Skips ahead to the packaging phase of the build |
| build local | Builds the CLI using local installs |
| build local:package | Skips ahead to the packaging phase of the build |

### Build Folder

#### Default
```
build ->
    config
    samples
    working ->
        in ->
            patients
            visits
            allergys
            problems
            medications
            immunizations
            procedures
        out ->
            patients
            visits
            allergys
            problems
            medications
            immunizations
            procedures
    csv-cli-1.0-SNAPSHOT.jar
```

#### Local
```
build ->
    apache-maven-3.6.3
    jdk8
    config
    samples
    working ->
        in ->
            patients
            visits
            allergys
            problems
            medications
            immunizations
            procedures
        out ->
            patients
            visits
            allergys
            problems
            medications
            immunizations
            procedures
    csv-cli-1.0-SNAPSHOT.jar
```

## Usage
The project provides a run script that will either use the local install of java or the global install specified by JAVA_HOME depending on how the project was built.

```
run

Running cli....

shell:>help
AVAILABLE COMMANDS

Built-In Commands 
        clear: Clear the shell screen.
        exit, quit: Exit the shell.
        help: Display help about available commands.
        history: Display or save the history of previously run commands
        script: Read and execute commands from a file.
        stacktrace: Display the full stacktrace of the last error.

List Models
        models: Lists the supported models.

Transformation
        split: Splits the files from the src to the dest.
        transform: Transforms the files from the src to the dest.

Validation
        validate: Validates the files from the src to the dest.

shell:>
```

### Disclaimer
Both the build and run scripts are provided for convienence and are not required to build / run the CLI.

## Configurations
The build script will provide an empty application.yml file in the build/config directory. The run script will configure spring to look for the application.yml from this directory. Below are the application specific properties. See [Spring common application properties](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html) for the full list of properties supported by the framework.

| Key | Default Value | Description |
| --- | --- | --- |
| application.output.json.records-per-file | 1-1 | The maximum records per file. Minimum is 20K. |
| application.output.csv.rows-per-file | 1-1 | The maximum rows per file. Minimum is 20K. |
| application.output.compress.enabled | false | Enables compressing the valid out files. |
| application.output.compress.entries-per-archive | Entire Directory | Enables spliting the output directory into smaller archives to match upload requirements. |

### Sample applicaton.yml
```
# Example Spring configuration property
debug: false

# Application properties
# These setting are specific to your needs. Ideally the setting would align with your upload requirements.
# Aka. batch size, number of comservers, etc
# These can also be used on the export side to aid clients in limiting the size of the exported files.
application:
  output:
    compress:
      enabled: true
      entries-per-archive: 5
    json:
      records-per-file: 150000
    csv:
      rows-per-file: 200000
```

## Directories
The service provides the ability to define your own directories or to use the default working directory. The directory should have subdirectories for each model. The service will read and write to the model specific subdirectories for you. Only the parent path is required.

## Commands
The following commands are supported along with [spring-shell's](https://spring.io/projects/spring-shell) built in commands.

| Name | Action| 
| --- | --- |
| models | Lists the models supported. |
| transform | Transforms the csv files from the src directory to JSON |
| validate | Validates the csv files from the src directory |
| split | Splits the source (.csv, .txt, .etc) files |

### Models
Lists the models supported along with their aliases.

```
shell:>models

-----------------------------------------
Supported Models:
-----------------------------------------
Patient Demographics: [p, d, patients]
Patient Visits: [v, visits]
Patient Allergies: [a, allergys]
Patient Problems: [prob, problems]
Patient Medications: [m, medications]
Patient Immunizations: [i, immunizations]
Patient Procedures: [proc, procedures]
```

### Transform
Transform takes in three arguments. The data model, the "in" directory to scan and the "out" directory to write the json files to. The output file will maintain the original csv file name. Upon completion the csv file will be deleted from the "in" directory. Any invalid (missing required fields) records will be written to an error file to work post conversion. The error file will maintain the original csv file name with -err.csv appended. The error reason will be written to (csv filename)-err.log file.

```
shell:>help transform


NAME
        transform - Transforms the files from the src to the dest.

SYNOPSYS
        transform [--model] string  [[--src-dir] string]  [[--dest-dir] string]

OPTIONS
        --model  string
                The alias for the model domain / entity.
                [Mandatory]

        --src-dir  string
                CSV files to transform.
                [Optional, default = build/working/in]

        --dest-dir  string
                JSON files.
                [Optional, default = build/working/out]
```

### Validate
Validate takes in three arguments. The data model, the "in" directory to scan and the "out" directory to write the csv files to. The output csv file will maintain the original csv file name. Upon completion the source csv file will be deleted from the "in" directory. Any invalid records will be written to an error file to work post validation. The error file will maintain the original csv file name with -err.csv appended. The error reason will be written to (csv filename)-err.log file.

```
shell:>help validate


NAME
        validate - Validates the files from the src to the dest.

SYNOPSYS
        validate [--model] string  [[--src-dir] string]  [[--dest-dir] string]

OPTIONS
        --model  string
                The alias for the model domain / entity.
                [Mandatory]

        --src-dir  string
                CSV files to validate.
                [Optional, default = build/working/in]

        --dest-dir  string
                Validated CSV files.
                [Optional, default = build/working/out]
```

### Split
Splits the files based on the desired number of lines. This transformation works for simple file formats (.csv, .txt) where splitting the file based on the number of lines won't corrupt the output file.

```
shell:>help split


NAME
        split - Splits the files from the src to the dest.

SYNOPSYS
        split [--lines-per-file] int  [--extension] string  [[--src-dir] string]  [[--dest-dir] string]

OPTIONS
        --lines-per-file  int
                The number of lines per file.
                [Mandatory]

        --extension  string
                File extension (.csv, .txt).
                [Mandatory]

        --src-dir  string
                Source directory.
                [Optional, default = build/working/in]

        --dest-dir  string
                Destination directory.
                [Optional, default = build/working/out]
```

### Packaging
To prevent the output directory for each model from becoming cluttered (unusable) and to aid in the movement of the output files. Two measures will be provide. First, a new directory will be created with the current date time in UTC as the directory name. This directory will contain all of the output files produced for that invocation of the command. Second, once the validation / transformation has completed the valid / good files will be packaged in a zip file if the compress output configuration is enabled. The good files will be deleted from the output directory. The invalid records along with the .log file(s) will remain in the output directory. The zip file name will match the name of the output directory. At the end of an invocation of the transform / validate commands the output directory for the invocation will at minimum contain the resulting zip file. 

## System Properties
The following system properties are supported.

| Name | Action| Default |
| --- | --- | --- |
| cli.verbose | Enables verbose logging | disabled |

### cli.verbose
When verbose logging is enabled the status, elapsed time along with the stacktrace of any error that occurred during the command is logged to the console.

```
run verbose
shell:>validate allergys src/test/resources/in src/test/resources/out

Running command...
-----------------------------------------
Status: OK
-----------------------------------------
Total time:  0.13 s
-----------------------------------------
```

# Results
These results are by no means complete. To get a complete picture of the performance additional profiling is needed via a true profiler. Monitoring resource usage via Windows Task Manager along with the displayed elapsed time were employed.

## Transform
With cli.debug enabled the elapsed time was ~69s for the same data used to benchmark [csv_to_json](https://github.com/chaseste/csv_to_json#results). This is a substantial improvement from a best of ~151 seconds by Python when compiled to C. This nets a tps (transformations per second) of ~15,443 compared to a best of ~7004 tps for Python. Scaled out to minutes, assumming garbage collection is consistent, nets a tpm (transformations per minute) of ~927K. Both Python and Java used ~16% CPU though Java used significantly more memory (~1 GB) while Python was ~5 MBs. Additional profiling beyong elapsed time is required though for situations where memory isn't an issue Java seems to be king.

| Records | Elapsed | TPS | TPM |
| --- | --- | --- | --- |
| 1062500 | 68.8 s | 15443 | 926,599 |

## Validation
With cli.debug enabled the elapsed time was ~64s for the same data. This nets a VPS (validations per second) of ~16,724. Scaled out to minutes, assumming garbage collection is consistent, nets a VPM (validations per minute) of ~1M. CPU usage along with memory consumption were essentially the same as above.

| Records | Elapsed | VPS | VPM |
| --- | --- | --- | --- |
| 1062500 | 63.53 s | 16724 | 1003463 |

# What's Left?
- Harden parser
- Add unit tests
- Additional transforms?
- Additional validation?
- Implement combine transform?
