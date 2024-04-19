package com.example.mahoamk;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    private UserService userService = new UserService();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel, 0, 0);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 1);
        PasswordField passInput = new PasswordField();
        GridPane.setConstraints(passInput, 1, 1);

        Button loginButton = new Button("Log In");
        GridPane.setConstraints(loginButton, 1, 2);
        loginButton.setOnAction(e -> {
            String username = nameInput.getText();
            String password = passInput.getText();
            if (userService.authenticate(username, password)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Successful");
                alert.setHeaderText(null);
                alert.setContentText("Welcome, " + username + "!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password.");
                alert.showAndWait();
            }
        });

        Button registerButton = new Button("Register");
        GridPane.setConstraints(registerButton, 1, 3);
        registerButton.setOnAction(e -> {
            String username = nameInput.getText();
            String password = passInput.getText();
            if (userService.register(username, password)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registration Successful");
                alert.setHeaderText(null);
                alert.setContentText("Welcome, " + username + "!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registration Failed");
                alert.setHeaderText(null);
                alert.setContentText("Username already exists.");
                alert.showAndWait();
            }
        });

        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton, registerButton);

        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
