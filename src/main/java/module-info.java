module com.example.libraryproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j.core;
    requires org.apache.logging.log4j;


    opens com.example.libraryproject to javafx.fxml;
    exports com.example.libraryproject;
    exports com.example.libraryproject.LibProject;
    opens com.example.libraryproject.LibProject to javafx.fxml;
}