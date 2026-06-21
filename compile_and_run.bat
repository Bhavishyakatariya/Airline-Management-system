@echo off
echo Compiling Java files...

:: Set the classpath to include both the current directory and the required libraries
set CLASSPATH=.;.\lib\rs2xml.jar;.\lib\jcalendar-tz-1.3.3-4.jar;.\lib\mysql-connector-j-9.2.0\mysql-connector-j-9.2.0.jar

:: Compile all Java files in the airlinemanagementsystem package
javac airlinemanagementsystem\*.java

:: Check if compilation was successful
if %errorlevel% equ 0 (
    echo Compilation successful!
    echo Running application...
    java airlinemanagementsystem.Login
) else (
    echo Compilation failed!
)

pause
