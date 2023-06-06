package source.application;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start (Stage stage) {
        try {
            new Menu(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setHeight(960);
        stage.setWidth(1280);
        stage.setTitle("Shop Application");
        stage.setResizable(false);
        stage.getIcons().add(new Image("icon.png"));
        stage.show();

    }
}
