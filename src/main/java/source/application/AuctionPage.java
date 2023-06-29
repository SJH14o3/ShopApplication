package source.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AuctionPage {
    public static int AUCTION_ID;
    public static double highestBid;
    public AuctionPage(Stage stage, int in) throws IOException {
        AUCTION_ID = in;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("auction.fxml")));
        Scene scene = new Scene(root, Color.WHITE);
        stage.setScene(scene);
    }
}
