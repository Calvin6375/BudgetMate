package com.budgetmate.controllers;

import com.budgetmate.models.Budget;
import com.budgetmate.models.Category;
import com.budgetmate.models.Salary;
import com.budgetmate.services.BudgetService;
import com.budgetmate.services.FileStorageService;
import com.budgetmate.utils.Calculator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.IOException;

/**
 * Unified controller handling both Salary and Budget tabs.
 */
public class MainController {
    // Salary Tab Controls
    @FXML
    private TextField grossSalaryField;
    @FXML
    private TextField taxField;
    @FXML
    private TextField deductionsField;
    @FXML
    private Label netSalaryLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Button calculateBtn;
    @FXML
    private Button saveSalaryBtn;

    // Budget Tab Controls
    @FXML
    private TextField categoryNameField;
    @FXML
    private TextField categoryAmountField;
    @FXML
    private TableView<Category> categoriesTable;
    @FXML
    private TableColumn<Category, String> categoryNameColumn;
    @FXML
    private TableColumn<Category, Double> categoryAmountColumn;
    @FXML
    private Label totalAllocatedLabel;
    @FXML
    private Label remainingBalanceLabel;
    @FXML
    private Label statusLabelBudget;
    @FXML
    private Label netSalaryLabelBudget;
    @FXML
    private Button addCategoryBtn;
    @FXML
    private Button removeCategoryBtn;
    @FXML
    private Button saveBudgetBtn;

    private Salary salary;
    private Budget budget;
    private FileStorageService storageService;
    private ObservableList<Category> categoryList;

    /**
     * Initializes the controller and sets up all components.
     */
    @FXML
    public void initialize() {
        storageService = new FileStorageService();
        salary = new Salary();
        budget = new Budget();
        categoryList = FXCollections.observableArrayList();

        // Configure table columns
        categoryNameColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        categoryAmountColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getAmount()).asObject());

        categoriesTable.setItems(categoryList);

        // Set button actions
        calculateBtn.setOnAction(event -> handleCalculateSalary());
        saveSalaryBtn.setOnAction(event -> handleSaveSalary());
        addCategoryBtn.setOnAction(event -> handleAddCategory());
        removeCategoryBtn.setOnAction(event -> handleRemoveCategory());
        saveBudgetBtn.setOnAction(event -> handleSaveBudget());

        loadData();
    }

    /**
     * Handles the calculate salary button action.
     */
    private void handleCalculateSalary() {
        try {
            double gross = Calculator.parseDouble(grossSalaryField.getText());
            double tax = Calculator.parseDouble(taxField.getText());
            double deductions = Calculator.parseDouble(deductionsField.getText());

            if (gross < 0 || tax < 0 || deductions < 0) {
                statusLabel.setText("Error: Values cannot be negative");
                statusLabel.setTextFill(Color.RED);
                return;
            }

            salary.setGrossSalary(gross);
            salary.setTax(tax);
            salary.setDeductions(deductions);

            double netSalary = salary.getNetSalary();
            netSalaryLabel.setText("Net Salary: \$" + Calculator.formatMoney(netSalary));
            netSalaryLabel.setStyle("-fx-font-size: 18; -fx-text-fill: #007BFF; -fx-font-weight: bold;");
            
            netSalaryLabelBudget.setText("Net Salary: \$" + Calculator.formatMoney(netSalary));

            updateBudgetLabels();
            statusLabel.setText("Salary calculated successfully!");
            statusLabel.setTextFill(Color.GREEN);

        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
            statusLabel.setTextFill(Color.RED);
        }
    }

    /**
     * Saves the salary data to a JSON file.
     */
    private void handleSaveSalary() {
        try {
            storageService.saveSalary(salary);
            statusLabel.setText("Salary data saved successfully!");
            statusLabel.setTextFill(Color.GREEN);
        } catch (IOException e) {
            statusLabel.setText("Error saving salary: " + e.getMessage());
            statusLabel.setTextFill(Color.RED);
        }
    }

    /**
     * Handles the add category button action.
     */
    private void handleAddCategory() {
        try {
            String categoryName = categoryNameField.getText().trim();
            double amount = Calculator.parseDouble(categoryAmountField.getText());

            if (!BudgetService.isValidCategoryName(categoryName)) {
                statusLabelBudget.setText("Error: Category name cannot be empty");
                statusLabelBudget.setTextFill(Color.RED);
                return;
            }

            if (!BudgetService.isValidAmount(amount)) {
                statusLabelBudget.setText("Error: Amount must be greater than 0");
                statusLabelBudget.setTextFill(Color.RED);
                return;
            }

            Category category = new Category(categoryName, amount);
            budget.addCategory(category);
            categoryList.add(category);

            categoryNameField.clear();
            categoryAmountField.clear();

            updateBudgetLabels();
            statusLabelBudget.setText("Category added successfully!");
            statusLabelBudget.setTextFill(Color.GREEN);

        } catch (Exception e) {
            statusLabelBudget.setText("Error: " + e.getMessage());
            statusLabelBudget.setTextFill(Color.RED);
        }
    }

    /**
     * Handles the remove category button action.
     */
    private void handleRemoveCategory() {
        Category selected = categoriesTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            budget.removeCategory(selected.getName());
            categoryList.remove(selected);
            updateBudgetLabels();
            statusLabelBudget.setText("Category removed successfully!");
            statusLabelBudget.setTextFill(Color.GREEN);
        } else {
            statusLabelBudget.setText("Error: Please select a category to remove");
            statusLabelBudget.setTextFill(Color.RED);
        }
    }

    /**
     * Saves the budget data to a JSON file.
     */
    private void handleSaveBudget() {
        try {
            storageService.saveBudget(budget);
            statusLabelBudget.setText("Budget saved successfully!");
            statusLabelBudget.setTextFill(Color.GREEN);
        } catch (IOException e) {
            statusLabelBudget.setText("Error saving budget: " + e.getMessage());
            statusLabelBudget.setTextFill(Color.RED);
        }
    }

    /**
     * Updates budget-related labels with current values.
     */
    private void updateBudgetLabels() {
        double netSalary = salary.getNetSalary();
        double totalAllocated = budget.getTotalAllocated();
        double remainingBalance = BudgetService.calculateRemainingBalance(salary, budget);

        netSalaryLabelBudget.setText("Net Salary: \$" + Calculator.formatMoney(netSalary));
        totalAllocatedLabel.setText("Total Allocated: \$" + Calculator.formatMoney(totalAllocated));

        if (remainingBalance < 0) {
            remainingBalanceLabel.setTextFill(Color.RED);
            remainingBalanceLabel.setText("Remaining Balance: -\$" + Calculator.formatMoney(Math.abs(remainingBalance)) + " (EXCEEDED)");
        } else {
            remainingBalanceLabel.setTextFill(Color.GREEN);
            remainingBalanceLabel.setText("Remaining Balance: \$" + Calculator.formatMoney(remainingBalance));
        }
    }

    /**
     * Loads all data from storage files.
     */
    private void loadData() {
        try {
            // Load salary
            salary = storageService.loadSalary();
            if (salary.getGrossSalary() > 0) {
                grossSalaryField.setText(String.valueOf(salary.getGrossSalary()));
                taxField.setText(String.valueOf(salary.getTax()));
                deductionsField.setText(String.valueOf(salary.getDeductions()));
                double netSalary = salary.getNetSalary();
                netSalaryLabel.setText("Net Salary: \$" + Calculator.formatMoney(netSalary));
                netSalaryLabel.setStyle("-fx-font-size: 18; -fx-text-fill: #007BFF; -fx-font-weight: bold;");
                netSalaryLabelBudget.setText("Net Salary: \$" + Calculator.formatMoney(netSalary));
            }

            // Load budget
            budget = storageService.loadBudget();
            if (budget != null && !budget.getCategories().isEmpty()) {
                categoryList.addAll(budget.getCategories());
            }

            updateBudgetLabels();
        } catch (IOException e) {
            statusLabel.setText("No saved data found");
        }
    }
}
