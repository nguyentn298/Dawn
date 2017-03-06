@echo off
set DB_USER=root
set DB_PASS=
set DB_NAME=hostpro_sharpinu
set DB_HOST=192.168.14.168
set MYSQL_DIR=D:/Database/mysql/bin/
echo =========================================================
echo db_user is %DB_USER%
echo db_pass is %DB_PASS%
echo db_name is %DB_NAME%
echo db_host is %DB_HOST% 

set WORKING_FOLDER=%~dp0
cd /d %WORKING_FOLDER%

set FILE_COUNTER=1
set COUNTER=1
echo =========================================================
:Loop
	set SCRIPT_FILE_PATTERN=%COUNTER%_*.sql
	for /f "tokens=*" %%f IN ('dir /b %SCRIPT_FILE_PATTERN% 2^>NUL') do (
		if exist %%f (
			echo %FILE_COUNTER%. Start loading file %%f...
			%MYSQL_DIR%mysql --host=%DB_HOST% --user=%DB_USER% --password=%DB_PASS% %DB_NAME% < "%%f"
			set /a FILE_COUNTER=FILE_COUNTER+1
		)
	)
	set /a COUNTER=COUNTER+1
	if %COUNTER%==50 goto End
goto Loop

:End
echo =========================================================
echo FINISHED!
pause
