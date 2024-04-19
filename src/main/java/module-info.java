module com.example.mahoamk {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.mahoamk to javafx.fxml;
    exports com.example.mahoamk;
}