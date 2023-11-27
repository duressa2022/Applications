module com.example.xogame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.Application.xogame to javafx.fxml;
    exports com.Application.xogame;
}