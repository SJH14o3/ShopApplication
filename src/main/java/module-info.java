module source.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;
    requires java.mail;


    opens source.application to javafx.fxml;
    exports source.application;
  //  exports source.test;
    //opens source.test to javafx.fxml;
}