module com.example.agenciacertificadora {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.agenciacertificadora to javafx.fxml;
    exports com.example.agenciacertificadora;
}