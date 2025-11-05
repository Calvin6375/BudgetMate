package com.budgetmate.models;

/**
 * Represents a salary calculation with gross amount, tax, and deductions.
 * Provides method to calculate net salary.
 */
public class Salary {
    private double grossSalary;
    private double tax;
    private double deductions;

    /**
     * Constructor for Salary.
     *
     * @param grossSalary  The gross salary amount
     * @param tax          The tax amount
     * @param deductions   The total deductions amount
     */
    public Salary(double grossSalary, double tax, double deductions) {
        this.grossSalary = grossSalary;
        this.tax = tax;
        this.deductions = deductions;
    }

    /**
     * Default constructor for Salary.
     */
    public Salary() {
        this(0, 0, 0);
    }

    /**
     * Calculates and returns the net salary.
     * Formula: Net Salary = Gross Salary - Tax - Deductions
     *
     * @return The net salary
     */
    public double getNetSalary() {
        return grossSalary - tax - deductions;
    }

    // Getters and Setters
    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "grossSalary=" + grossSalary +
                ", tax=" + tax +
                ", deductions=" + deductions +
                ", netSalary=" + getNetSalary() +
                '}';
    }
}
