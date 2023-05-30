module source.application {
    requires javafx.controls;
    requires javafx.fxml;


    opens source.application to javafx.fxml;
    exports source.application;
}