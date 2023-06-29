package source.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import source.Global;

import java.io.IOException;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start (Stage stage) throws IOException {
        Global.setStage(stage, 12345678);
//        try {
//            new Menu(stage);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        stage.setHeight(720);
//        stage.setWidth(1280);
//        stage.setTitle("Shop Application");
//        stage.setResizable(false);
//        stage.getIcons().add(new Image("icon.png"));
//        stage.show();
        stage.getIcons().add(new Image("icon.png"));
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root ,776 , 448 ));
        stage.show();



    }
}
