package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("Enter Celsius:");
        TextField celsiusInput = new TextField();
        Button convertButton = new Button("Convert");
        Label resultLabel = new Label();

        convertButton.setOnAction(e -> {
            try {
                double c = Double.parseDouble(celsiusInput.getText().trim());
                double f = (c * 9 / 5) + 32;
                resultLabel.setText("Fahrenheit: " + f);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input!");
            }
        });

        VBox root = new VBox(10, label, celsiusInput, convertButton, resultLabel);
        root.setStyle("-fx-padding: 16;");
        stage.setTitle("Celsius → Fahrenheit");
        stage.setScene(new Scene(root, 300, 180));
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}



