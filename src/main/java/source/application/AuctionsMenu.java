package source.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AuctionsMenu {
    public AuctionsMenu(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("auctionPage.fxml")));
        Scene scene = new Scene(root, Color.WHITE);
        stage.setScene(scene);
    }
}
