package com.example.texteditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/*
 * File: Main.java
 * Description: A text editor with style change,
 * undo and redo functions using stacks.
 * Authors:
 *   - Pavel Sushkov
 * Copyright: (c) 2024 Pavel Sushkov
 * License: This file is licensed under the MIT License.
 */

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Binding java file with fxml
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        //Creating scene
        Scene scene = new Scene(root);
        //set Title for stage
        stage.setTitle("Text Editor");
        //set icon image for app
        Image icon = new Image("E:\\Projects\\TextEditor\\src\\main\\java\\com\\example\\texteditor\\icon.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}