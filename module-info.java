module com.example.texteditor {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.desktop;

    opens com.example.texteditor to javafx.fxml;
    exports com.example.texteditor;
}