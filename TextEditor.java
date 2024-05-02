package com.example.texteditor;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
/*
 * File: Main.java
 * Description: A text editor with style change,
 * undo and redo functions using stacks.
 * Authors:
 *   - Pavel Sushkov
 * Copyright: (c) 2024 Pavel Sushkov
 * License: This file is licensed under the MIT License.
 */


public class TextEditor {
    /** caves the content of the given TextArea to a file selected by the user.
     * @param textArea the textArea containing the text to be saved. This represents the text field of the editor.
     * @param stage the stage object representing the current window or dialog. This is used to display the file chooser dialog.
     */
    protected void onSave(TextArea textArea, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Text File");
        //restriction on saving in text format only
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            // reading from a text field to a file
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void onClose() {
        System.exit(0);
    }

    /**
     * clears the content of the given TextArea.
     * @param textArea the textArea to clear. This represents the text field of the editor.
     */
    protected void onNew(TextArea textArea) { textArea.clear(); }

    /**
     * clearing the text field
     * @param textArea to read the written text
     * @param myStack the elements are written character by character
     */
    protected void onUndoClick(TextArea textArea, Stack myStack) {
        if (!textArea.getText().isEmpty()) {
            //look through the text,adding each character to the stack
            for (int i = 0; i < textArea.getText().length(); i++) {
                myStack.push(textArea.getText().charAt(i));
            }
            //clearing the field at the end
            textArea.clear();
        }
    }

    /**
     * canceling the cleaning operation
     * @param myStack stack with saved data from the undo method
     * @param redoResult to write to the text field
     * @param textArea The TextArea where the result of the redo operation will be displayed. This represents the text field of the editor.
     */
    protected void onRedoClick(Stack myStack,String redoResult, TextArea textArea) {
        StringBuilder sbRedo = new StringBuilder();
        if(!myStack.isEmpty()) {
            while (!myStack.isEmpty()) {
                sbRedo.append(myStack.pop());
            }
            //writing the stack elements to a string in reverse
            redoResult = sbRedo.reverse().toString();
            //add result
            textArea.setText(redoResult);
        }
    }

    /**
     * initialization colors
     * @param myColor choice box
     * @param colors lots of colors
     * @param textArea where the text is displayed. The selected font color from the choice box will be applied to this text area.
     */
    protected void initColors(ChoiceBox <String> myColor, ObservableList <String> colors,TextArea textArea) {
        myColor.setValue("Black");
        myColor.setItems(colors);
        myColor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            onChangeFontColor(newValue, textArea);
        });
    }

    /**
     * initialization fonts
     * @param myFontStyle choice box
     * @param fonts lots of fonts
     * @param textArea where the text is displayed. The selected font style from the choice box will be applied to this text area.
     */
    protected void initFonts(ChoiceBox <String> myFontStyle, ObservableList <String> fonts,TextArea textArea) {
        myFontStyle.setValue("System");
        myFontStyle.setItems(fonts);
        myFontStyle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            onChangeFontStyle(newValue, textArea);
        });
    }

    /**
     * initialization sizes
     * @param myFontSize choice box
     * @param sizes lots of sizes
     * @param textArea where the text is displayed. The selected font size from the choice box will be applied to this text area.
     */
    protected void initSize(ChoiceBox <String> myFontSize, ObservableList <String> sizes,TextArea textArea) {
        myFontSize.setValue("12");
        myFontSize.setItems(sizes);
        myFontSize.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            onChangeFontSize(newValue, textArea);
        });
    }

    /**
     * change color font
     * @param myColor color from choice box
     * @param textArea where the text is displayed. The font color will be changed according to the selected color.
     */
    protected void onChangeFontColor(String myColor, TextArea textArea) {
        switch (myColor) {
            case "Yellow" -> textArea.setStyle("-fx-text-fill: yellow;");
            case "Red" -> textArea.setStyle("-fx-text-fill: red;");
            case "Green" -> textArea.setStyle("-fx-text-fill: green;");
            case "Blue" -> textArea.setStyle("-fx-text-fill: blue;");
            default -> textArea.setStyle("-fx-text-fill: black;");
        }
    }

    /**
     * change font
     * @param myFontStyle fonts from choice box
     * @param textArea where the text is displayed. The font style will be changed according to the selected font.
     */
    protected void onChangeFontStyle (String myFontStyle, TextArea textArea) {
        switch (myFontStyle) {
            case "Calibri" -> textArea.setStyle("-fx-font-family: \"Calibri\";");
            case "Century" -> textArea.setStyle("-fx-font-family: \"Century\";");
            case "Montserrat" -> textArea.setStyle("-fx-font-family: \"Montserrat\";");
            case "Georgia" -> textArea.setStyle("-fx-font-family: \"Georgia\";");
            case "Lucida console" -> textArea.setStyle("-fx-font-family: \"Lucida console\";");
            default -> textArea.setStyle("-fx-font-family: \"System\";");
        }
    }

    /**
     * change font size
     * @param myFontSize size of fonts from choice box
     * @param textArea where the text is displayed. The font size will be changed according to the selected size.
     */
    protected void onChangeFontSize(String myFontSize, TextArea textArea) {
        switch (myFontSize) {
            case "8" -> textArea.setStyle("-fx-font-size: 8px");
            case "12" -> textArea.setStyle("-fx-font-size: 12px");
            case "20" -> textArea.setStyle("-fx-font-size: 20px");
            case "24" -> textArea.setStyle("-fx-font-size: 24px");
            case "36" -> textArea.setStyle("-fx-font-size: 36px");
            case "48" -> textArea.setStyle("-fx-font-size: 48px");
            case "64" -> textArea.setStyle("-fx-font-size: 64px");
            case "128" -> textArea.setStyle("-fx-font-size: 128px");
            default -> textArea.setStyle("-fx-font-size: 16px");
        }
    }
}
