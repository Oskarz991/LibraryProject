module com.example.libraryproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.libraryproject to javafx.fxml;
    exports com.example.libraryproject;
    exports com.example.libraryproject.TestPackage;
    opens com.example.libraryproject.TestPackage to javafx.fxml;
}