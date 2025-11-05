package com.budgetmate.services;

import com.budgetmate.models.Budget;
import com.budgetmate.models.Category;
import com.budgetmate.models.Salary;

/**
 * Service class for budget-related business logic operations.
 */
public class BudgetService {

    /**
     * Calculates the remaining balance after budgeting.
     *
     * @param salary The salary object
     * @param budget The budget object
     * @return The remaining balance
     */
    public static double calculateRemainingBalance(Salary salary, Budget budget) {
        double netSalary = salary.getNetSalary();
        return budget.getRemainingBalance(netSalary);
    }

    /**
     * Validates that a category name is not empty.
     *
     * @param categoryName The category name to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidCategoryName(String categoryName) {
        return categoryName != null && !categoryName.trim().isEmpty();
    }

    /**
     * Validates that an amount is positive.
     *
     * @param amount The amount to validate
     * @return true if positive, false otherwise
     */
    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    /**
     * Checks if budget exceeds net salary.
     *
     * @param budget   The budget object
     * @param netSalary The net salary amount
     * @return true if budget exceeds salary, false otherwise
     */
    public static boolean isBudgetExceeded(Budget budget, double netSalary) {
        return budget.getTotalAllocated() > netSalary;
    }

    /**
     * Gets a category by name from the budget.
     *
     * @param budget The budget object
     * @param name   The category name to find
     * @return The category if found, null otherwise
     */
    public static Category getCategoryByName(Budget budget, String name) {
        return budget.getCategories().stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
