@echo off


REM check JAVA_HOME & java
if "%JAVA_HOME%" == "" goto noJavaHome
if exist "%JAVA_HOME%\bin\java.exe" goto mainEntry
:noJavaHome
echo ---------------------------------------------------
echo Error: JAVA_HOME environment variable is not set. 
echo ---------------------------------------------------
goto end

:mainEntry
REM set HOME_DIR
set "CURR_DIR=%cd%"
cd ..
set "JOB_HOME=%cd%"
cd %CURR_DIR%

if exist "%JOB_HOME%\bin\startup.bat" goto okHome
echo ---------------------------------------------------
echo Error: COMM_HOME environment variable is not defined correctly.
echo ---------------------------------------------------
goto end



:okHome


REM startup Server
set "RUN_CMD=java -Xms128m -Xmx2048m -server -Dlog4j_root=%JOB_HOME%\logs"
set "RUN_CMD=%RUN_CMD% -Djob.home="%JOB_HOME%""
set "RUN_CMD=%RUN_CMD% -cp "%JOB_HOME%\conf;%JOB_HOME%\lib\*""
set "RUN_CMD=%RUN_CMD% com.netposa.rom.WebsocketCmdApplication"
echo "%RUN_CMD%"
call %RUN_CMD%


:end


