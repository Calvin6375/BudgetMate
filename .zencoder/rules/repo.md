---
description: Repository Information Overview
alwaysApply: true
---

# BudgetMate - JavaFX Desktop Application

## Summary

BudgetMate is a lightweight JavaFX desktop application designed to help users calculate and manage their salary and budget effectively. The application provides functionality to calculate net salary from gross income, taxes, and deductions, and enables users to create and track budget categories with real-time balance calculations. All data is persisted locally using JSON file storage.

## Repository Structure

`
BudgetMate/
├── src/main/java/com/budgetmate/
│   ├── Main.java                          # JavaFX application entry point
│   ├── controllers/
│   │   └── MainController.java            # Unified UI controller for both tabs
│   ├── models/
│   │   ├── Salary.java                    # Salary model with net calculation
│   │   ├── Budget.java                    # Budget model with category management
│   │   └── Category.java                  # Budget category model
│   ├── services/
│   │   ├── FileStorageService.java        # JSON file persistence layer
│   │   └── BudgetService.java             # Business logic service
│   └── utils/
│       └── Calculator.java                # Utility for calculations and formatting
├── src/main/resources/
│   ├── ui/
│   │   └── main.fxml                      # JavaFX UI definition (tabbed interface)
│   └── config/
│       └── settings.properties            # Application configuration
├── src/test/java/com/budgetmate/
│   └── BudgetTests.java                   # Unit tests (JUnit 4)
├── build.gradle                           # Gradle build configuration
├── README.md                              # Project documentation
├── .gitignore                             # Git ignore rules
└── gradlew                                # Gradle wrapper script
`

## Language & Runtime

**Language**: Java  
**Version**: Java 17+  
**Build System**: Gradle  
**Package Manager**: Gradle (Maven Central)  
**IDE Support**: IntelliJ IDEA, Visual Studio Code, Eclipse

## Dependencies

**Core Framework**:
- JavaFX 21.0.1 (Controls, FXML, Graphics)
- Gson 2.10.1 (JSON serialization)

**Testing**:
- JUnit 4.13.2 (Unit testing framework)

**Development**:
- Gradle 7.0+ (Build tool)
- Java 17+ (Runtime)

## Build & Installation

Build the project:
`ash
gradle build
`

Run the application:
`ash
gradle run
`

Run tests:
`ash
gradle test
`

Clean build artifacts:
`ash
gradle clean
`

## Main Entry Points

**Application Launch**: src/main/java/com/budgetmate/Main.java
- Entry point: main(String[] args) method
- Starts JavaFX application with main.fxml UI
- Window title: "BudgetMate - Salary & Budget Manager"
- Default window size: 900x750 pixels

**UI Controller**: src/main/java/com/budgetmate/controllers/MainController.java
- Handles both Salary Calculator and Budget Manager tabs
- Manages salary input validation and calculations
- Manages budget category CRUD operations
- Handles data persistence through FileStorageService

## Testing

**Framework**: JUnit 4  
**Test Location**: src/test/java/com/budgetmate/BudgetTests.java  
**Test File Naming Convention**: *Tests.java  
**Configuration**: Default JUnit 4 configuration in build.gradle  

**Run Command**:
`ash
gradle test
`

**Sample Tests**:
- Salary net calculation validation
- Category addition and total allocation tracking
- Remaining balance calculations
- Budget exceeded detection

## Data Persistence

**Storage Format**: JSON  
**Storage Location**: udget_data/ directory (created at runtime)  
**Files Created**:
- udget_data/salary.json - Persisted salary information
- udget_data/budget.json - Persisted budget categories

## UI Components

**Main Interface**: Tabbed interface with two tabs

1. **Salary Calculator Tab**
   - Gross Salary input field
   - Tax amount input field
   - Deductions amount input field
   - Net Salary calculation display
   - Calculate and Save buttons

2. **Budget Manager Tab**
   - Net Salary display
   - Category name input
   - Category amount input
   - Categories TableView (displays all budget categories)
   - Add/Remove category buttons
   - Total allocated and remaining balance displays
   - Save budget button

**Design**: Clean minimal UI with white background and blue (#007BFF) accents

## Configuration Files

- **settings.properties**: Application settings (version, theme, storage format)
- **.gitignore**: Git exclusion patterns for build artifacts, IDE files, and runtime data
- **build.gradle**: Gradle build configuration with dependencies and tasks

## Key Features

- Real-time net salary calculation (Gross - Tax - Deductions)
- Dynamic budget category management
- Automatic remaining balance calculation
- Budget exceeded warning (red highlighting when budget > net salary)
- Local data persistence with JSON
- Responsive tabbed UI with JavaFX
- Input validation for currency values
- Professional error handling and status messaging
