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
    public void start (Stage stage) throws IOException {
        new Menu(stage);
        stage.setTitle("Shop Application");
        stage.setResizable(false);
        stage.getIcons().add(new Image("icon.png"));
        stage.show();
    }
}
