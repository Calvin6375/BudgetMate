package com.budgetmate;

import com.budgetmate.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main entry point for the BudgetMate JavaFX application.
 * Loads the main UI from main.fxml and displays the primary stage.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the main FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/main.fxml"));
        Parent root = loader.load();

        // Create the scene with the root node
        Scene scene = new Scene(root, 900, 750);

        // Set up the primary stage
        primaryStage.setTitle("BudgetMate - Salary & Budget Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
