package com.budgetmate.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a budget with multiple categories and total allocation logic.
 */
public class Budget {
    private List<Category> categories;
    private double totalAllocated;

    /**
     * Constructor for Budget.
     */
    public Budget() {
        this.categories = new ArrayList<>();
        this.totalAllocated = 0;
    }

    /**
     * Adds a category to the budget.
     *
     * @param category The category to add
     */
    public void addCategory(Category category) {
        categories.add(category);
        updateTotalAllocated();
    }

    /**
     * Removes a category from the budget by name.
     *
     * @param categoryName The name of the category to remove
     * @return true if removed, false if not found
     */
    public boolean removeCategory(String categoryName) {
        boolean removed = categories.removeIf(c -> c.getName().equals(categoryName));
        if (removed) {
            updateTotalAllocated();
        }
        return removed;
    }

    /**
     * Updates the total allocated amount by summing all categories.
     */
    private void updateTotalAllocated() {
        totalAllocated = categories.stream().mapToDouble(Category::getAmount).sum();
    }

    /**
     * Gets the remaining balance after budget allocation.
     *
     * @param netSalary The net salary amount
     * @return The remaining balance
     */
    public double getRemainingBalance(double netSalary) {
        return netSalary - totalAllocated;
    }

    // Getters and Setters
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        updateTotalAllocated();
    }

    public double getTotalAllocated() {
        return totalAllocated;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "categories=" + categories +
                ", totalAllocated=" + totalAllocated +
                '}';
    }
}
