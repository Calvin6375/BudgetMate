package com.budgetmate.services;

import com.budgetmate.models.Budget;
import com.budgetmate.models.Category;
import com.budgetmate.models.Salary;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Service for saving and loading budget data to/from JSON files.
 */
public class FileStorageService {
    private static final String DATA_FOLDER = "budget_data";
    private static final String SALARY_FILE = "salary.json";
    private static final String BUDGET_FILE = "budget.json";

    private final Gson gson;

    /**
     * Constructor for FileStorageService.
     */
    public FileStorageService() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        ensureDataFolder();
    }

    /**
     * Ensures the data folder exists.
     */
    private void ensureDataFolder() {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    /**
     * Saves the salary object to a JSON file.
     *
     * @param salary The salary object to save
     * @throws IOException if an error occurs during file writing
     */
    public void saveSalary(Salary salary) throws IOException {
        String json = gson.toJson(salary);
        try (FileWriter writer = new FileWriter(new File(DATA_FOLDER, SALARY_FILE))) {
            writer.write(json);
        }
    }

    /**
     * Loads the salary object from a JSON file.
     *
     * @return The loaded Salary object, or a new empty Salary if file not found
     * @throws IOException if an error occurs during file reading
     */
    public Salary loadSalary() throws IOException {
        File file = new File(DATA_FOLDER, SALARY_FILE);
        if (!file.exists()) {
            return new Salary();
        }

        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, Salary.class);
        }
    }

    /**
     * Saves the budget object to a JSON file.
     *
     * @param budget The budget object to save
     * @throws IOException if an error occurs during file writing
     */
    public void saveBudget(Budget budget) throws IOException {
        String json = gson.toJson(budget);
        try (FileWriter writer = new FileWriter(new File(DATA_FOLDER, BUDGET_FILE))) {
            writer.write(json);
        }
    }

    /**
     * Loads the budget object from a JSON file.
     *
     * @return The loaded Budget object, or a new empty Budget if file not found
     * @throws IOException if an error occurs during file reading
     */
    public Budget loadBudget() throws IOException {
        File file = new File(DATA_FOLDER, BUDGET_FILE);
        if (!file.exists()) {
            return new Budget();
        }

        try (FileReader reader = new FileReader(file)) {
            Budget budget = gson.fromJson(reader, Budget.class);
            return budget != null ? budget : new Budget();
        }
    }
}
