package source.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import source.Global;
import source.threads.ProductPreLoadThread;
import source.threads.ProductPreLoadThread;

import java.io.File;
import java.io.IOException;

public class Main extends Application{
    public static Thread preLoadProducts = new ProductPreLoadThread();

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start (Stage stage) throws IOException {

        String mediaPath = "background.mp3";
        Media media = new Media(new File(mediaPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.03);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        Global.setStage(stage, 12345678);
        preLoadProducts.start();
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
        stage.setResizable(false);
        stage.setX(571);
        stage.setY(200);
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root ,776 , 448 ));
        stage.show();
    }
}
