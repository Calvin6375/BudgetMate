package com.budgetmate.utils;

import java.text.DecimalFormat;

/**
 * Utility class for calculation-related operations.
 */
public class Calculator {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    /**
     * Formats a number to 2 decimal places.
     *
     * @param value The value to format
     * @return The formatted string
     */
    public static String formatMoney(double value) {
        return DECIMAL_FORMAT.format(value);
    }

    /**
     * Parses a string to double, returning 0 if parsing fails.
     *
     * @param value The string value to parse
     * @return The parsed double, or 0 if parsing fails
     */
    public static double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Calculates percentage of total.
     *
     * @param value The value
     * @param total The total
     * @return The percentage
     */
    public static double calculatePercentage(double value, double total) {
        if (total == 0) return 0;
        return (value / total) * 100;
    }
}
