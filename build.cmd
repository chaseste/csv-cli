@ECHO OFF

setlocal
set "PROJECT_DIR=%~dp0"
cd "%PROJECT_DIR%"

set BUILD_ARG=%1
IF "%BUILD_ARG%"=="" (
    echo.
    set /p BUILD_ARG="build (enter for default): "
)

echo.
echo Building CLI...
echo Project Directory: %PROJECT_DIR%
echo Build Arg: %BUILD_ARG% 

net session >nul 2>&1
IF errorlevel 1 (
    echo.
    echo Build is not running as administrator...
)

set BUILD_ENV="global"
IF "%BUILD_ARG%"=="" GOTO :install
IF "%BUILD_ARG%"=="package" (
    IF exist "%PROJECT_DIR%\build" (
        GOTO :working-dirs
    )

    echo.
    echo Build hasn't run before. Please run full build first
    echo.
    GOTO :exit
)

set BUILD_ENV="local"
IF "%BUILD_ARG%"=="local" GOTO :install
IF "%BUILD_ARG%"=="local:package" (
    IF exist "%PROJECT_DIR%\build\apache-maven-3.6.3\bin\mvn.cmd" (
        GOTO :working-dirs
    ) ELSE (
        echo.
        echo Build hasn't run before. Please run full build first
        echo.
        GOTO :exit
    )
)

echo.
echo Invalid option [%BUILD_ARG%]
echo.
echo Valid Options:
echo package or local or local:package
echo.
GOTO :exit

:install
IF exist "%PROJECT_DIR%\build" (
    IF %BUILD_ENV% == "global" (
        IF exist "%PROJECT_DIR%\build\jdk8" (
            echo.
            echo Checking if JDK is installed...

            (wmic product get name| findstr /i /C:"8 Update 131 (64-bit)")&&(
                echo.
                echo Attempting to uninstall JDK...
                wmic product where name="Java 8 Update 131 (64-bit)" call uninstall /nointeractive
                wmic product where name="Java SE Development Kit 8 Update 131 (64-bit)" call uninstall /nointeractive
            ) 
            
            (wmic product get name| findstr /i /C:"8 Update 131 (64-bit)")&&(
                echo.
                echo Unable to uninstall JDK. Please manually uninstall or run as admin before proceeding...
                echo.
                GOTO :exit
            ) 
        ) ELSE (
            echo.
            echo Skipping install since build already exists...
            GOTO :working-dirs
        )
    ) ELSE (
        IF exist "%PROJECT_DIR%\build\apache-maven-3.6.3\bin\mvn.cmd" (
            echo.
            echo Skipping install since build already exists...
            GOTO :working-dirs
        )
    )

    echo.
    echo Cleaning %PROJECT_DIR%\build
    rd /s /q "%PROJECT_DIR%\build"
)

:install
echo.
echo Creating build directory
mkdir "%PROJECT_DIR%\build"
IF %BUILD_ENV% == "global" (
    GOTO :package
)

@REM Install Java
echo.
echo Download Java
curl -jkL -H "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-windows-x64.exe -o "%PROJECT_DIR%\build\jdk-8u131-windows-x64.exe"

echo.
echo Install Java
"%PROJECT_DIR%\build\jdk-8u131-windows-x64.exe" /s INSTALLDIR="%PROJECT_DIR%\build\jdk8"
if errorlevel 1 GOTO :exit

@REM Install Maven
echo.
echo Download Maven
curl http://apache.mirrors.hoobly.com/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip -o "%PROJECT_DIR%\build\apache-maven-3.6.3-bin.zip"

echo.
echo Extract Maven
tar -xf "%PROJECT_DIR%\build\apache-maven-3.6.3-bin.zip" -C "%PROJECT_DIR%\build"
if errorlevel 1 GOTO :exit

:working-dirs
set SKIP_STEP=""
if exist "%PROJECT_DIR%\build\working" (
    set /p SKIP_STEP="Skip creating working dirs (y/n): "
)
if "%SKIP_STEP%" == "y" GOTO :samples

echo.
echo Creating working directory
mkdir "%PROJECT_DIR%\build\working"

mkdir "%PROJECT_DIR%\build\working\in"
mkdir "%PROJECT_DIR%\build\working\in\patients"
mkdir "%PROJECT_DIR%\build\working\in\visits"
mkdir "%PROJECT_DIR%\build\working\in\allergys"
mkdir "%PROJECT_DIR%\build\working\in\problems"
mkdir "%PROJECT_DIR%\build\working\in\medications"
mkdir "%PROJECT_DIR%\build\working\in\immunizations"
mkdir "%PROJECT_DIR%\build\working\in\procedures"

mkdir "%PROJECT_DIR%\build\working\out"
mkdir "%PROJECT_DIR%\build\working\out\patients"
mkdir "%PROJECT_DIR%\build\working\out\visits"
mkdir "%PROJECT_DIR%\build\working\out\allergys"
mkdir "%PROJECT_DIR%\build\working\out\problems"
mkdir "%PROJECT_DIR%\build\working\out\medications"
mkdir "%PROJECT_DIR%\build\working\out\immunizations"
mkdir "%PROJECT_DIR%\build\working\out\procedures"

:samples
if exist "%PROJECT_DIR%\build\samples" (
    set /p SKIP_STEP="Skip samples (y/n): "
)
if "%SKIP_STEP%" == "y" GOTO :config

echo.
echo Copying Samples
mkdir "%PROJECT_DIR%\build\samples"
xcopy /f "%PROJECT_DIR%\src\test\resources\*.csv" "%PROJECT_DIR%\build\samples"

:config
if exist "%PROJECT_DIR%\build\config" (
    set /p SKIP_STEP="Skip config (y/n): "
)
if "%SKIP_STEP%" == "y" GOTO :packaging

echo.
echo Copying Config
mkdir "%PROJECT_DIR%\build\config"
xcopy /f "%PROJECT_DIR%\src\test\resources\config\*.yml" "%PROJECT_DIR%\build\config"

:packaging
IF %BUILD_ENV% == "global" (
    GOTO :package
)

:local-package
set MAVEN_HOME=%PROJECT_DIR%\build\apache-maven-3.6.3\bin
set JAVA_HOME=%PROJECT_DIR%\build\jdk8

:package
if exist "%PROJECT_DIR%\build\csv-cli-1.0-SNAPSHOT.jar" (
    echo.
    echo Cleaning up previous build...
    echo %PROJECT_DIR%\build\csv-cli-1.0-SNAPSHOT.jar
    del "%PROJECT_DIR%\build\csv-cli-1.0-SNAPSHOT.jar" /f /q
) 

echo.
echo Packaging CLI...

set MAVEN_ARGS=-B
IF %BUILD_ENV% == "local" (
    set MAVEN_ARGS=%MAVEN_ARGS% -Dmaven.repo.local="%PROJECT_DIR%\build\.m2\repository"
)

echo MAVEN_HOME [%MAVEN_HOME%]
echo JAVA_HOME [%JAVA_HOME%]
echo MAVEN_ARGS [%MAVEN_ARGS%]

echo.
echo Building jar
call "%MAVEN_HOME%\mvn" %MAVEN_ARGS% clean package
if errorlevel 1 GOTO :exit

echo.
echo Copying jar
xcopy /f "%PROJECT_DIR%\target\csv-cli-1.0-SNAPSHOT.jar" "%PROJECT_DIR%\build\"
endlocal

echo.
echo Done!
echo.

:exit
pause
exit /b
