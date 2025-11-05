package com.budgetmate;

import com.budgetmate.models.Budget;
import com.budgetmate.models.Category;
import com.budgetmate.models.Salary;
import com.budgetmate.services.BudgetService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for BudgetMate application logic.
 */
public class BudgetTests {
    private Salary salary;
    private Budget budget;

    @Before
    public void setUp() {
        salary = new Salary(5000, 500, 200);
        budget = new Budget();
    }

    @Test
    public void testNetSalaryCalculation() {
        double netSalary = salary.getNetSalary();
        assertEquals(4300, netSalary, 0.01);
    }

    @Test
    public void testAddCategory() {
        Category category = new Category("Rent", 1000);
        budget.addCategory(category);
        assertEquals(1, budget.getCategories().size());
        assertEquals(1000, budget.getTotalAllocated(), 0.01);
    }

    @Test
    public void testRemainingBalance() {
        budget.addCategory(new Category("Rent", 1000));
        budget.addCategory(new Category("Food", 500));
        double remaining = BudgetService.calculateRemainingBalance(salary, budget);
        assertEquals(2800, remaining, 0.01);
    }

    @Test
    public void testBudgetExceeded() {
        budget.addCategory(new Category("Rent", 5000));
        assertTrue(BudgetService.isBudgetExceeded(budget, salary.getNetSalary()));
    }
}
