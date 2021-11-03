module com.example.pdfreadergui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.pdfreadergui to javafx.fxml;
    exports com.example.pdfreadergui;
}