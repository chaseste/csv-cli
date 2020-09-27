@ECHO OFF

setlocal
set "PROJECT_DIR=%~dp0"
IF NOT exist "%PROJECT_DIR%\build\csv-cli-1.0-SNAPSHOT.jar" (
    echo.
    echo Please run build.
    exit /b
)

IF exist "%PROJECT_DIR%\build\jdk8\bin" (
     set JAVA_EXEC="%PROJECT_DIR%\build\jdk8\bin\java"
) ELSE (
    set JAVA_EXEC="%JAVA_HOME%\bin\java"
)

set SYSTEM_PROPS=
IF "%1"=="verbose" (
    set SYSTEM_PROPS=-Dcli.verbose=true
)

echo.
echo Running cli....
echo.
%JAVA_EXEC% -jar %SYSTEM_PROPS% "%PROJECT_DIR%\build\csv-cli-1.0-SNAPSHOT.jar" --spring.config.location="file:%PROJECT_DIR%/build/config/"

:exit
endlocal
pause
exit /b