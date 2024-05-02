package com.example.texteditor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
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
public class SceneController {
    TextEditor textEditor = new TextEditor();
    @FXML
    private TextArea textArea;
    @FXML
    private String redoResult;
    @FXML
    private final Stage stage = new Stage();
    @FXML
    private final Stack<Character> myStack = new Stack<>();
    @FXML
    private ChoiceBox<String> myFontStyle;
    @FXML
    private ChoiceBox<String> myFontSize;
    @FXML
    private ChoiceBox<String> myColor;
    @FXML
    private final ObservableList<String> fonts = FXCollections
            .observableArrayList("System", "Calibri", "Century",
                    "Montserrat", "Georgia", "Lucida console");
    @FXML
    private final ObservableList<String> sizes = FXCollections
            .observableArrayList("8","12","16","20","24","36","48","64","128");
    @FXML
    private final ObservableList<String> colors = FXCollections
            .observableArrayList("Red","Green","Blue","Black","Yellow");

    @FXML
    private void initialize () {
        textEditor.initFonts(myFontStyle,fonts,textArea);
        textEditor.initSize(myFontSize,sizes,textArea);
        textEditor.initColors(myColor,colors,textArea);
    }

    @FXML
    protected void onUndoClick() {
        textEditor.onUndoClick(textArea,myStack);
    }
    @FXML
    protected void onRedoClick() {
       textEditor.onRedoClick(myStack,redoResult,textArea);
    }
    @FXML
    protected void onSave() {
        textEditor.onSave(textArea,stage);
    }
    @FXML
    protected void onClose() { textEditor.onClose(); }
    @FXML
    protected void onNew() { textEditor.onNew(textArea); }
    @FXML
    protected void onChangeFontColor() {
        textEditor.onChangeFontColor(myColor.getValue(),textArea); }
    @FXML
    protected void onChangeFontStyle() {
        textEditor.onChangeFontStyle(myFontStyle.getValue(),textArea);
    }

}