module com.example.xoxgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.xoxgame to javafx.fxml;
    exports com.example.xoxgame;
}