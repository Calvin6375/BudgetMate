# BudgetMate

A lightweight JavaFX desktop application for managing salary and budgeting. This project helps users calculate their net salary and track budget allocations across different categories.

## Features

- Calculate net salary from gross salary, tax, and deductions
- Create and manage budget categories
- Track remaining balance after budgeting
- Save and load budget data locally (JSON format)
- Clean, minimal JavaFX UI with tabbed interface

## Requirements

- Java 17+
- Gradle 7.0+

## Build & Run

Build the project:
\\\ash
gradle build
\\\

Run the application:
\\\ash
gradle run
\\\

Run tests:
\\\ash
gradle test
\\\

## Project Structure

- **src/main/java/com/budgetmate**: Main Java source files
  - **Main.java**: JavaFX application entry point
  - **controllers**: MVC controllers (MainController)
  - **models**: Data models (Salary, Budget, Category)
  - **services**: Business logic (FileStorageService, BudgetService)
  - **utils**: Utility classes (Calculator)
- **src/main/resources**: FXML and configuration files
- **src/test**: Unit tests

## Usage

1. Enter your gross salary, tax amount, and deductions in the Salary Calculator tab
2. Click Calculate Salary to see your net salary
3. Go to Budget Manager tab to create budget categories
4. Add categories (like Rent, Food, Transport) with allocated amounts
5. View remaining balance in real-time
6. Save your data for later use
