package source.application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class Menu {
    private void load(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu.fxml")));
        Scene scene = new Scene(root, Color.WHITE);
        stage.setScene(scene);
    }
    public Menu(Stage stage) throws IOException {
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.setTitle("Shop Application");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loading.fxml")));
        Scene scene = new Scene(root, Color.WHITE);
        stage.setScene(scene);
        try {
            Main.preLoadProducts.join();
            Timeline loadTimer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
                try {
                    load(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }));
            loadTimer.setCycleCount(1);
            loadTimer.play();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}