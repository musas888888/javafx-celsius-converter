package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static org.example.Converter.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        ComboBox<String> mode = new ComboBox<>();
        mode.getItems().addAll("Fahrenheit → Celsius", "Kelvin → Celsius", "Celsius → Kelvin");
        mode.getSelectionModel().selectFirst();

        TextField input = new TextField();
        input.setPromptText("Enter value");
        Button convert = new Button("Convert");
        Label out = new Label();

        convert.setOnAction(e -> {
            try {
                double v = Double.parseDouble(input.getText().trim());
                String m = mode.getValue();
                double res;
                if (m.startsWith("Fahrenheit"))      res = fToC(v);
                else if (m.startsWith("Kelvin"))      res = kToC(v);
                else                                   res = cToK(v);
                out.setText(String.format("Result: %.2f", res));
            } catch (NumberFormatException ex) {
                out.setText("Invalid input!");
            }
        });

        VBox root = new VBox(10,
                new Label("Choose conversion:"), mode,
                new Label("Value:"), input, convert, out);
        root.setStyle("-fx-padding: 16;");
        stage.setTitle("Temperature Converter");
        stage.setScene(new Scene(root, 320, 220));
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}




