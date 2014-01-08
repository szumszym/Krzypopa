@ECHO off

:: Change these variables to reflect your system
set app=D:\development\works\Krzypopa\
set tomcat=D:\development\tools\apache-tomcat-7.0.47\
set maven=D:\development\tools\apache-maven-3.1.1\

:: Kill tomcat server if it is running
cd /d %tomcat%bin\
call shutdown.bat

:: Build using maven
cd /d %app%
call %maven%bin\mvn.bat clean install -DskipTests

:: Delete old compiled servlet
cd /d %tomcat%webapps\
if exist bookingsystem (
rmdir bookingsystem /s /q
del bookingsystem.war /F
)

set warFile=%app%war\target\bookingsystem.war

if exist %warFile% (
goto fileexists
goto alldone
) else (
goto alldone
)

:fileexists

::Copy war file to tomcat webapps dir
xcopy "%warFile%" "%tomcat%webapps"

:: Initiate tomcat server
cd /d %tomcat%bin\
call startup.bat

:: Wait for server to start before calling deploy
echo ==================================================================
echo = Starting Tomcat Server. Wait until it starts, then press a key =
echo ==================================================================
pause

:: Start up webview
start http://localhost:8181/bookingsystem/

:: Echo out that we are done
echo.
echo ==============================
echo = Done running deploy script =
echo ==============================

:alldone
:: Return to original directory (for conveinence)
cd /d %~dp0
