package com.kiwi.monthlymoneymanager.controllers;

/**
 *
 * @author tgrishkian, lfabbian
 */

/*public class BudgetController {

    private static Map<String, BudgetItem> budgetItems = new HashMap<>();

    public static class BudgetItem {
        private Category category; // No String for category
        private double amountTarget;
        private Month month;  // Using the Month Enum instead of String
        private String description;

        public BudgetItem(Category category, double amountTarget, Month month, String description) {
            this.category = category;
            this.amountTarget = amountTarget;
            this.month = month;
            this.description = description;
        }
    }

// The following methods will have a lot of .getName() since the Month and Category are not Strings but an object and enum

    // Method for an addition of a budget item
    public static void addBudgetItem(Category category, double amountTarget, Month month, String description) {
        BudgetItem newItem = new BudgetItem(category, amountTarget, month, description);
        budgetItems.put(category.getName() + "-" + month, newItem);  // Combining category name and month for a unique key
    }

    // Method for editing an existing budget item
    public static void editBudgetItem(Category category, double newAmountTarget, Month newMonth, String newDescription) {
        if (budgetItems.containsKey(category.getName() + "-" + newMonth)) {
            BudgetItem item = budgetItems.get(category.getName() + "-" + newMonth);
            item.amountTarget = newAmountTarget;
            item.month = newMonth;
            item.description = newDescription;
        }
    }

    // Method here is used for a removal of a budget item
    public static void removeBudgetItem(Category category, Month month) {
        budgetItems.remove(category.getName() + "-" + month);
    }

    // Retrieve a budget item by category and month
    public static BudgetItem getBudgetItem(Category category, Month month) {
        return budgetItems.get(category.getName() + "-" + month);
    }
}*/