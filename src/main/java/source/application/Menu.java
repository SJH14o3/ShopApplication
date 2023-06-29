package source.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Menu {
    public Menu(Stage stage) throws IOException {
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.setTitle("Shop Application");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu.fxml")));
        Scene scene = new Scene(root, Color.WHITE);
        stage.setScene(scene);
    }
}