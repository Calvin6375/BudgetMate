@echo off
REM BudgetMate Gradle Build and Run Script for Windows

echo.
echo ========================================
echo     BudgetMate - Build and Run
echo ========================================
echo.

REM Check if gradlew exists
if not exist gradlew (
    echo Error: gradlew not found. Please ensure you are in the project root directory.
    pause
    exit /b 1
)

REM Build the project
echo Building BudgetMate...
call gradlew.bat build

if errorlevel 1 (
    echo Build failed! Please check the errors above.
    pause
    exit /b 1
)

echo.
echo Build successful! Starting application...
echo.

REM Run the application
call gradlew.bat run

pause
