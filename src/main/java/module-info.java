module source.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens source.application to javafx.fxml;
    exports source.application;
}