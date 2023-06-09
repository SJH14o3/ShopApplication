package source.application;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import source.Global;

import java.io.IOException;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start (Stage stage) {
        Global.setStage(stage, 1234);
        try {
            new Menu(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setHeight(720);
        stage.setWidth(1280);
        stage.setTitle("Shop Application");
        stage.setResizable(false);
        stage.getIcons().add(new Image("icon.png"));
        stage.show();

    }
}
