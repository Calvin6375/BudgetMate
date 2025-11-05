package com.budgetmate.models;

/**
 * Represents a budget category with a name and allocated amount.
 */
public class Category {
    private String name;
    private double amount;

    /**
     * Constructor for Category.
     *
     * @param name   The name of the category (e.g., "Rent", "Food")
     * @param amount The budget amount allocated to this category
     */
    public Category(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    /**
     * Default constructor for Category.
     */
    public Category() {
        this("", 0);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
